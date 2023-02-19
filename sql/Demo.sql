USE SchoolAccess;
GO

SELECT * FROM Users;
GO

SELECT * FROM Roles;
GO

SELECT * FROM AccessCards;
GO

SELECT * FROM AccessHistory;
GO

SELECT * FROM vActiveCards;
GO

SELECT * FROM vAccessedInLast30Days;
GO


DECLARE @Ret INT;
DECLARE @Start DATE=DATEADD(DAY, -60, GETDATE());
DECLARE @End DATE=GETDATE();

EXEC @Ret = dbo.udfDaysAttended @UserID=2, @StartDate=@Start, @EndDate=@End;

SELECT N'Total days attended: ' + STR(@Ret);
GO