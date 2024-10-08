USE [StudentManagement]
GO
/****** Object:  Table [dbo].[DangKyThi]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DangKyThi](
	[MaHS] [int] NOT NULL,
	[MaMH_HK] [varchar](2) NOT NULL,
	[SoLuong] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Diem]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Diem](
	[MaHS] [int] NOT NULL,
	[MaMH_HK] [varchar](2) NULL,
	[Diem] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GiaoVien]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiaoVien](
	[MaGV] [int] IDENTITY(1,1) NOT NULL,
	[TenGV] [nvarchar](50) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[MaMH] [char](1) NOT NULL,
 CONSTRAINT [PK__GiaoVien__2725AEF3489B1B6E] PRIMARY KEY CLUSTERED 
(
	[MaGV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HocKi]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HocKi](
	[MaHK] [int] IDENTITY(1,1) NOT NULL,
	[TenHK] [int] NOT NULL,
 CONSTRAINT [PK__HocKi__2725A6E7F971DB1B] PRIMARY KEY CLUSTERED 
(
	[MaHK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HocSinh]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HocSinh](
	[MaHS] [int] IDENTITY(1,1) NOT NULL,
	[HoDem] [nvarchar](30) NOT NULL,
	[Ten] [nvarchar](10) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[MaLop] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHS] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lop]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lop](
	[MaLop] [int] IDENTITY(1,1) NOT NULL,
	[TenLop] [varchar](5) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLop] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MonHoc]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonHoc](
	[MaMH] [char](1) NOT NULL,
	[TenMH] [nvarchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MonHoc_HocKi]    Script Date: 7/14/2024 11:38:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonHoc_HocKi](
	[MaHK] [int] NOT NULL,
	[MaMH] [char](1) NOT NULL,
	[MaMH_HK] [varchar](2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaMH_HK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[DangKyThi] ([MaHS], [MaMH_HK], [SoLuong]) VALUES (1, N'a1', 1)
INSERT [dbo].[DangKyThi] ([MaHS], [MaMH_HK], [SoLuong]) VALUES (1, N'b1', 1)
INSERT [dbo].[DangKyThi] ([MaHS], [MaMH_HK], [SoLuong]) VALUES (1, N'c1', 2)
INSERT [dbo].[DangKyThi] ([MaHS], [MaMH_HK], [SoLuong]) VALUES (2, N'a1', 2)
GO
INSERT [dbo].[Diem] ([MaHS], [MaMH_HK], [Diem]) VALUES (1, N'a1', 9)
INSERT [dbo].[Diem] ([MaHS], [MaMH_HK], [Diem]) VALUES (2, N'b1', 9)
INSERT [dbo].[Diem] ([MaHS], [MaMH_HK], [Diem]) VALUES (3, N'c1', 10)
GO
SET IDENTITY_INSERT [dbo].[GiaoVien] ON 

INSERT [dbo].[GiaoVien] ([MaGV], [TenGV], [NgaySinh], [GioiTinh], [MaMH]) VALUES (2, N'Lô Thị Mận', CAST(N'2024-04-05' AS Date), 1, N'a')
INSERT [dbo].[GiaoVien] ([MaGV], [TenGV], [NgaySinh], [GioiTinh], [MaMH]) VALUES (3, N'Trương Hoài Đức', CAST(N'2024-04-11' AS Date), 1, N'b')
SET IDENTITY_INSERT [dbo].[GiaoVien] OFF
GO
SET IDENTITY_INSERT [dbo].[HocKi] ON 

INSERT [dbo].[HocKi] ([MaHK], [TenHK]) VALUES (1, 1)
INSERT [dbo].[HocKi] ([MaHK], [TenHK]) VALUES (2, 2)
SET IDENTITY_INSERT [dbo].[HocKi] OFF
GO
SET IDENTITY_INSERT [dbo].[HocSinh] ON 

INSERT [dbo].[HocSinh] ([MaHS], [HoDem], [Ten], [NgaySinh], [GioiTinh], [MaLop]) VALUES (1, N'Phan Duy', N'Thành', CAST(N'2003-03-23' AS Date), 1, 1)
INSERT [dbo].[HocSinh] ([MaHS], [HoDem], [Ten], [NgaySinh], [GioiTinh], [MaLop]) VALUES (2, N'Phan Duy ', N'Khánh', CAST(N'2003-02-12' AS Date), 1, 2)
INSERT [dbo].[HocSinh] ([MaHS], [HoDem], [Ten], [NgaySinh], [GioiTinh], [MaLop]) VALUES (3, N'Đậu Phúc', N'Chương', CAST(N'1995-04-25' AS Date), 1, 1)
INSERT [dbo].[HocSinh] ([MaHS], [HoDem], [Ten], [NgaySinh], [GioiTinh], [MaLop]) VALUES (6, N'Đậu Phúc', N'Hoàng', CAST(N'2003-04-25' AS Date), 0, 1)
INSERT [dbo].[HocSinh] ([MaHS], [HoDem], [Ten], [NgaySinh], [GioiTinh], [MaLop]) VALUES (7, N'aaaaaa', N'aaaaaaa', CAST(N'2024-04-10' AS Date), 1, 1)
SET IDENTITY_INSERT [dbo].[HocSinh] OFF
GO
SET IDENTITY_INSERT [dbo].[Lop] ON 

INSERT [dbo].[Lop] ([MaLop], [TenLop]) VALUES (1, N'10A1')
INSERT [dbo].[Lop] ([MaLop], [TenLop]) VALUES (2, N'10A2')
INSERT [dbo].[Lop] ([MaLop], [TenLop]) VALUES (3, N'10A3')
INSERT [dbo].[Lop] ([MaLop], [TenLop]) VALUES (4, N'11A1')
INSERT [dbo].[Lop] ([MaLop], [TenLop]) VALUES (5, N'11A2')
INSERT [dbo].[Lop] ([MaLop], [TenLop]) VALUES (6, N'12A1')
INSERT [dbo].[Lop] ([MaLop], [TenLop]) VALUES (7, N'12A2')
SET IDENTITY_INSERT [dbo].[Lop] OFF
GO
INSERT [dbo].[MonHoc] ([MaMH], [TenMH]) VALUES (N'a', N'Toán')
INSERT [dbo].[MonHoc] ([MaMH], [TenMH]) VALUES (N'b', N'Lý')
INSERT [dbo].[MonHoc] ([MaMH], [TenMH]) VALUES (N'c', N'Hóa')
GO
INSERT [dbo].[MonHoc_HocKi] ([MaHK], [MaMH], [MaMH_HK]) VALUES (1, N'a', N'a1')
INSERT [dbo].[MonHoc_HocKi] ([MaHK], [MaMH], [MaMH_HK]) VALUES (2, N'a', N'a2')
INSERT [dbo].[MonHoc_HocKi] ([MaHK], [MaMH], [MaMH_HK]) VALUES (1, N'b', N'b1')
INSERT [dbo].[MonHoc_HocKi] ([MaHK], [MaMH], [MaMH_HK]) VALUES (2, N'c', N'c1')
GO
ALTER TABLE [dbo].[DangKyThi]  WITH CHECK ADD  CONSTRAINT [FK__DangKyThi__MaHS__4D5F7D71] FOREIGN KEY([MaHS])
REFERENCES [dbo].[HocSinh] ([MaHS])
GO
ALTER TABLE [dbo].[DangKyThi] CHECK CONSTRAINT [FK__DangKyThi__MaHS__4D5F7D71]
GO
ALTER TABLE [dbo].[DangKyThi]  WITH CHECK ADD  CONSTRAINT [FK_DangKyThi_MonHoc_HocKi] FOREIGN KEY([MaMH_HK])
REFERENCES [dbo].[MonHoc_HocKi] ([MaMH_HK])
GO
ALTER TABLE [dbo].[DangKyThi] CHECK CONSTRAINT [FK_DangKyThi_MonHoc_HocKi]
GO
ALTER TABLE [dbo].[Diem]  WITH CHECK ADD FOREIGN KEY([MaHS])
REFERENCES [dbo].[HocSinh] ([MaHS])
GO
ALTER TABLE [dbo].[Diem]  WITH CHECK ADD FOREIGN KEY([MaMH_HK])
REFERENCES [dbo].[MonHoc_HocKi] ([MaMH_HK])
GO
ALTER TABLE [dbo].[GiaoVien]  WITH CHECK ADD  CONSTRAINT [FK_GiaoVien_MonHoc] FOREIGN KEY([MaMH])
REFERENCES [dbo].[MonHoc] ([MaMH])
GO
ALTER TABLE [dbo].[GiaoVien] CHECK CONSTRAINT [FK_GiaoVien_MonHoc]
GO
ALTER TABLE [dbo].[HocSinh]  WITH CHECK ADD FOREIGN KEY([MaLop])
REFERENCES [dbo].[Lop] ([MaLop])
GO
ALTER TABLE [dbo].[MonHoc_HocKi]  WITH CHECK ADD  CONSTRAINT [FK__MonHoc_Hoc__MaHK__35BCFE0A] FOREIGN KEY([MaHK])
REFERENCES [dbo].[HocKi] ([MaHK])
GO
ALTER TABLE [dbo].[MonHoc_HocKi] CHECK CONSTRAINT [FK__MonHoc_Hoc__MaHK__35BCFE0A]
GO
ALTER TABLE [dbo].[MonHoc_HocKi]  WITH CHECK ADD FOREIGN KEY([MaMH])
REFERENCES [dbo].[MonHoc] ([MaMH])
GO
