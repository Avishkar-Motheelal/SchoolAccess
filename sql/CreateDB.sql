USE master;
GO

CREATE DATABASE SchoolAccess;
GO

USE SchoolAccess;
GO

CREATE TABLE [dbo].[Roles] 
(
    [RoleID] [INT] IDENTITY(1,1) PRIMARY KEY,
    [RoleName] [NVARCHAR](255) NOT NULL UNIQUE
);
GO

CREATE TABLE [dbo].[ContactDetails] 
(
    [ContactDetailsID] [INT] IDENTITY(1,1) PRIMARY KEY,
    [PhoneNumber] [NVARCHAR](20) NOT NULL
);
GO

CREATE TABLE [dbo].[Users] 
(
    [UserID] [INT] IDENTITY(1,1) PRIMARY KEY,
    [FirstName] [NVARCHAR](255) NOT NULL,
    [LastName] [NVARCHAR](255) NOT NULL,
    [ContactDetailsID] [INT] FOREIGN KEY REFERENCES ContactDetails(ContactDetailsID) NOT NULL,
    [RoleID] [INT] FOREIGN KEY REFERENCES Roles(RoleID) NOT NULL
);
GO

CREATE TABLE [dbo].[AccessCards] 
(
	[AccessCardID] [uniqueidentifier] PRIMARY KEY,
    [UserID] [INT] FOREIGN KEY REFERENCES Users(UserID) NOT NULL,
    [Activated] [BIT] NOT NULL
);
GO

CREATE TABLE [dbo].[AccessHistory] 
(
    [AccessHistoryID] [INT] IDENTITY(1,1) PRIMARY KEY,
    [AccessCardID] [UNIQUEIDENTIFIER] FOREIGN KEY REFERENCES AccessCards(AccessCardID) NOT NULL,
    [DateUsed] [DATETIMEOFFSET] NOT NULL,
    [WasAllowed] [BIT] NOT NULL
);
GO