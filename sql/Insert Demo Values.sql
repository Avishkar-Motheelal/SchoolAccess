USE SchoolAccess;
GO

INSERT INTO Roles (RoleName)
VALUES 
	(N'Staff'),
	(N'Student');
GO

EXEC	[dbo].[uspCreateNewUser]
		@FirstName = N'Bob',
		@LastName = N'Ross',
		@PhoneNumber = N'8291021192',
		@RoleID = 1

GO

EXEC	[dbo].[uspCreateNewUser]
		@FirstName = N'Bob',
		@LastName = N'Dylan',
		@PhoneNumber = N'324245435',
		@RoleID = 1

GO

EXEC	[dbo].[uspCreateNewUser]
		@FirstName = N'Barrack',
		@LastName = N'Osama',
		@PhoneNumber = N'3242911323',
		@RoleID = 2

GO

EXEC	[dbo].[uspCreateNewUser]
		@FirstName = N'Rob',
		@LastName = N'Ran',
		@PhoneNumber = N'854582394',
		@RoleID = 2

GO

EXEC	[dbo].[uspCreateNewUser]
		@FirstName = N'Sal',
		@LastName = N'Ross',
		@PhoneNumber = N'242341232',
		@RoleID = 1

GO

UPDATE AccessCards
SET Activated = 0
WHERE UserID = 3

GO

DECLARE @user1 UNIQUEIDENTIFIER;
SELECT @user1 = AccessCardID FROM AccessCards WHERE UserID = 1;
DECLARE @user2 UNIQUEIDENTIFIER;
SELECT @user2 = AccessCardID FROM AccessCards WHERE UserID = 2;
DECLARE @user3 UNIQUEIDENTIFIER;
SELECT @user3 = AccessCardID FROM AccessCards WHERE UserID = 3;
DECLARE @user4 UNIQUEIDENTIFIER;
SELECT @user4 = AccessCardID FROM AccessCards WHERE UserID = 4;
DECLARE @user5 UNIQUEIDENTIFIER;
SELECT @user5 = AccessCardID FROM AccessCards WHERE UserID = 5;
INSERT INTO AccessHistory (AccessCardID, DateUsed, WasAllowed)
VALUES 
	(@user1, TODATETIMEOFFSET(DATEADD(HOUR, 10, DATEADD(DAY, -30, GETDATE())), '+02:00'), 1),
	(@user1, TODATETIMEOFFSET(DATEADD(HOUR, 9, DATEADD(DAY, -28, GETDATE())), '+02:00'), 1),
	(@user2, TODATETIMEOFFSET(DATEADD(HOUR, 8, DATEADD(DAY, -34, GETDATE())), '+02:00'), 1),
	(@user2, TODATETIMEOFFSET(DATEADD(HOUR, 10, DATEADD(DAY, -42, GETDATE())), '+02:00'), 1),
	(@user3, TODATETIMEOFFSET(DATEADD(HOUR, 12, DATEADD(DAY, -19, GETDATE())), '+02:00'), 1),
	(@user3, TODATETIMEOFFSET(DATEADD(HOUR, 11, DATEADD(DAY, -1, GETDATE())), '+02:00'), 1),
	(@user4, TODATETIMEOFFSET(DATEADD(HOUR, 8, DATEADD(DAY, -11, GETDATE())), '+02:00'), 1),
	(@user4, TODATETIMEOFFSET(DATEADD(HOUR, 9, DATEADD(DAY, -4, GETDATE())), '+02:00'), 1),
	(@user4, TODATETIMEOFFSET(DATEADD(HOUR, 10, DATEADD(DAY, -2, GETDATE())), '+02:00'), 1),
	(@user5, TODATETIMEOFFSET(DATEADD(HOUR, 12, DATEADD(DAY, -19, GETDATE())), '+02:00'), 1),
	(@user5, TODATETIMEOFFSET(DATEADD(HOUR, 13, DATEADD(DAY, -1, GETDATE())), '+02:00'), 1),
	(@user5, TODATETIMEOFFSET(DATEADD(HOUR, 11, DATEADD(DAY, -2, GETDATE())), '+02:00'), 1),
	(@user5, TODATETIMEOFFSET(DATEADD(HOUR, 9, DATEADD(DAY, -4, GETDATE())), '+02:00'), 1),
	(@user5, TODATETIMEOFFSET(DATEADD(HOUR, 10, DATEADD(DAY, -11, GETDATE())), '+02:00'), 1);
GO