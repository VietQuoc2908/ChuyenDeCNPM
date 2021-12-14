Use Master
Go
Create Database DienThoaiSpringMVC
Go
Use DienThoaiSpringMVC
Go

Create Table DanhMuc
(
	tenDM nvarchar(100) not null PRIMARY KEY--Mới / Giảm giá / Chưa có
)

Create Table NhaSanXuat
(
	maNSX int IDENTITY(1,1) PRIMARY KEY,
	tenNSX nvarchar(50) not null,
	hinhAnh image,
	base64Image varchar(MAX)
)
Go

Create Table DienThoai
(
	maDT int IDENTITY(1,1) PRIMARY KEY,
	tenDT nvarchar(300) not null,
	maNSX int not null,
	tenDM nvarchar(100) not null,
	-- thông số --
	hinhAnh image,
	base64Image varchar(MAX),
	moTa nvarchar(MAX),
	manHinh nvarchar(MAX),
	heDieuHanh nvarchar(50),
	cameraTruoc nvarchar(MAX),
	cameraSau nvarchar(MAX),
	chip nvarchar(MAX),
	ram nvarchar(MAX),
	rom nvarchar(MAX),
	sim nvarchar(MAX),
	pin nvarchar(MAX),
	giaBan int not null,
	tonKho int not null,
	giamGia int not null default '0',
	FOREIGN KEY (maNSX) REFERENCES NhaSanXuat(maNSX),
	FOREIGN KEY (tenDM) REFERENCES DanhMuc(tenDM)
)
Go

Insert into DanhMuc values(N'Mới')
Insert into DanhMuc values(N'Giảm giá')
Insert into DanhMuc values(N'Chưa có')

Insert into NhaSanXuat values(N'SamSung')
Insert into NhaSanXuat values(N'Oppo')
