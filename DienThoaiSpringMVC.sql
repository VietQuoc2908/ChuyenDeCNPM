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
	giaBan float not null,
	tonKho int not null,
	giamGia int not null default '0',
	thanhTien float not null, -- giaBan * giamGia
	hienThiGiaBan varchar(50) not null,
	hienThiThanhTien varchar(50) not null,
	FOREIGN KEY (maNSX) REFERENCES NhaSanXuat(maNSX),
	FOREIGN KEY (tenDM) REFERENCES DanhMuc(tenDM)
)
Go

Create Table PhanQuyen
(
	maQuyen int IDENTITY(1,1) PRIMARY KEY,
	tenQuyen varchar(50) not null --ad,kh
)
Go

Create Table TaiKhoan
(
	taikhoan varchar(50) PRIMARY KEY,
	matkhau varchar(50) not null,
	maQuyen int not null,
	FOREIGN KEY (maQuyen) REFERENCES PhanQuyen(maQuyen)
)
Go

Create Table KhachHang
(
	maKH int IDENTITY(1,1) PRIMARY KEY,
	taikhoan varchar(50) not null,
	tenKH nvarchar(100) not null,
	sdt varchar(11) not null,
	diachi nvarchar(MAX) not null,
	FOREIGN KEY (taikhoan) REFERENCES TaiKhoan(taikhoan)
)
Go

Create Table GioHang
(
	maGH int IDENTITY(1,1) PRIMARY KEY,
	maKH int not null,
	tongGiaTien float not null,
	hienThiTongTien varchar(50) not null,
	FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
)
Go

Create Table ChiTietGioHang
(
	maCTGH int IDENTITY(1,1) PRIMARY KEY,
	maGH int not null,
	maDT int not null,
	soLuong int not null,
	donGia float not null,
	tongTien float not null,
	hienThiTongTien varchar(50) not null,
	FOREIGN KEY (maGH) REFERENCES GioHang(maGH),
	FOREIGN KEY (maDT) REFERENCES DienThoai(maDT)
)
Go

Create Table HoaDon
(
	maHD int IDENTITY(1,1) PRIMARY KEY,
	maKH int not null,
	status int default 0, --0:chờ xác nhận, 1:đang giao hàng, 2:đã giao, 3: đã huỷ
	tongTien float not null,
	hienThiTongTien varchar(50) not null,
	FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
)
Go

Create Table ChiTietHoaDon
(
	maCTHD int IDENTITY(1,1) PRIMARY KEY,
	maHD int not null,
	maDT int not null,
	soLuong int not null,
	donGia float not null,
	tongTien float not null,
	hienThiTongTien varchar(50) not null,
	FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
	FOREIGN KEY (maDT) REFERENCES DienThoai(maDT)
)
Go

Insert into DanhMuc values(N'Mới')
Insert into DanhMuc values(N'Giảm giá')
Insert into DanhMuc values(N'Chưa có')

Insert into PhanQuyen values('ad')
Insert into PhanQuyen values('kh')