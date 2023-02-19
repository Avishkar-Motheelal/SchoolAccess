USE SchoolAccess;
GO

-- Returns the total number of days a specific user attended within a date range
CREATE FUNCTION [dbo].[udfDaysAttended] (@UserID INT, @StartDate DATE, @EndDate DATE)
RETURNS INT
AS
BEGIN
	DECLARE @TotalDays INT;	

	SELECT @TotalDays = COUNT(*)
	FROM AccessHistory ah
	INNER JOIN AccessCards ac ON ac.AccessCardID = ah.AccessCardID
	WHERE ac.UserID = @UserID
		AND ah.DateUsed BETWEEN @StartDate AND @EndDate;

	RETURN @TotalDays;
END
GO
--
-- USP to create a new user
CREATE PROCEDURE [dbo].[uspCreateNewUser] @FirstName NVARCHAR(255), @LastName NVARCHAR(255), @PhoneNumber NVARCHAR(20), @RoleID INT
AS
BEGIN TRY
	BEGIN TRANSACTION InsertNewUser

	INSERT INTO ContactDetails (PhoneNumber)
	VALUES (@PhoneNumber);

	INSERT INTO Users (FirstName, LastName, ContactDetailsID, RoleID)
	VALUES (@FirstName, @LastName, SCOPE_IDENTITY(), @RoleID)

	DECLARE @uid UNIQUEIDENTIFIER;
	SET @uid  = NEWID();
	INSERT INTO AccessCards (AccessCardID,UserID, Activated)
	VALUES (@uid, SCOPE_IDENTITY(), 1)

	SELECT ac.AccessCardID, u.FirstName + ' ' + u.LastName AS FullName
	FROM AccessCards ac
	INNER JOIN Users u ON u.UserID = ac.UserID
	WHERE ac.AccessCardID = @uid
	COMMIT TRANSACTION
END TRY

BEGIN CATCH
	SELECT ERROR_MESSAGE() AS ErrorMessage

	ROLLBACK TRANSACTION
END CATCH
GO
--
-- USP to see who attended on a specific date
CREATE PROCEDURE [dbo].[uspWhoAttended] @Date DATE
AS
SELECT u.UserID, u.FirstName, u.LastName, r.RoleName
FROM AccessHistory ah
INNER JOIN AccessCards ac ON ac.AccessCardID = ah.AccessCardID
INNER JOIN Users u ON ac.UserID = u.UserID
INNER JOIN Roles r ON r.RoleID = u.RoleID
WHERE ah.DateUsed = @Date
ORDER BY r.RoleName ASC
GO
--
-- View to see which users have active access cards
CREATE VIEW vActiveCards
AS
SELECT u.UserID, u.FirstName, u.LastName, r.RoleName, cd.PhoneNumber
FROM AccessCards ac
INNER JOIN Users u ON u.UserID = ac.UserID
INNER JOIN ContactDetails cd ON cd.ContactDetailsID = u.ContactDetailsID
INNER JOIN Roles r ON r.RoleID = u.RoleID
WHERE ac.Activated = 1
GO
--
-- View to see who used their access card in the last 30 days
CREATE VIEW vAccessedInLast30Days
AS
SELECT u.UserID, u.FirstName, u.LastName, MAX(ah.DateUsed) AS LastUsed
FROM AccessHistory ah
INNER JOIN AccessCards ac ON ac.AccessCardID = ah.AccessCardID
INNER JOIN Users u ON ac.UserID = u.UserID
WHERE ah.DateUsed BETWEEN DATEADD(DAY, - 30, GETDATE()) AND GETDATE()
GROUP BY u.UserID, u.FirstName, u.LastName
GO