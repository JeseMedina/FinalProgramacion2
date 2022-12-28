-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-12-2022 a las 00:34:19
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `idAdmin` int(11) NOT NULL,
  `user` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`idAdmin`, `user`, `contrasena`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja`
--

CREATE TABLE `caja` (
  `idCaja` int(11) NOT NULL,
  `numeroCaja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `caja`
--

INSERT INTO `caja` (`idCaja`, `numeroCaja`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(6, 5),
(7, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `nombre`) VALUES
(2, 'Verduleria'),
(3, 'Carniceria'),
(4, 'Limpieza'),
(8, 'Higiene, Salud'),
(9, 'Niños y Bebés'),
(10, 'Lácteos y Huevos'),
(11, 'Bebidas'),
(12, 'Panaderia'),
(13, 'Farmacia'),
(14, 'Comestibles');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tel` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `dni`, `nombre`, `tel`) VALUES
(0, '0', 'Consumidor Final', '0'),
(1, '43344028', 'Jese Medina', '3644222222'),
(3, '12345678', 'rasr', '3644222222'),
(5, '345', 'hgjghj', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `idCompra` int(11) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  `numeroSerie` int(11) NOT NULL,
  `fechaCompras` date NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`idCompra`, `idProveedor`, `numeroSerie`, `fechaCompras`, `monto`) VALUES
(12, 1, 1, '2022-09-17', 500),
(13, 1, 2, '2022-09-17', 7200),
(14, 1, 3, '2022-09-17', 5000),
(15, 1, 4, '2022-09-17', 82830),
(18, 1, 5, '2022-12-04', 5500),
(22, 1, 6, '2022-12-04', 0),
(23, 1, 7, '2022-12-14', 62000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecompra`
--

CREATE TABLE `detallecompra` (
  `idDetalleCompra` int(11) NOT NULL,
  `idCompra` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioCompra` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detallecompra`
--

INSERT INTO `detallecompra` (`idDetalleCompra`, `idCompra`, `idProducto`, `cantidad`, `precioCompra`) VALUES
(1, 13, 2, 8, 7200),
(2, 14, 1, 10, 5000),
(3, 15, 1, 100, 50000),
(4, 15, 2, 10, 9000),
(5, 15, 3, 2, 30),
(6, 15, 4, 198, 19800),
(7, 15, 5, 8, 4000),
(8, 15, 1, 50, 30000),
(9, 15, 1, 10, 8000),
(10, 18, 2, 10, 5500),
(11, 18, 1, 2, 740),
(12, 18, 1, 10, 500),
(13, 18, 1, 110, 55000),
(14, 22, 1, 0, 0),
(15, 23, 1, 100, 62000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleflujocaja`
--

CREATE TABLE `detalleflujocaja` (
  `idDetalleFlujoCaja` int(11) NOT NULL,
  `idFlujoCaja` int(11) NOT NULL,
  `ingreso` double NOT NULL,
  `egreso` double NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalleflujocaja`
--

INSERT INTO `detalleflujocaja` (`idDetalleFlujoCaja`, `idFlujoCaja`, `ingreso`, `egreso`, `descripcion`) VALUES
(3, 6, 0, 10, 'Retiro'),
(4, 6, 0, 100, 'Retiro'),
(5, 6, 0, 100, 'Retiro'),
(6, 6, 100, 0, 'Deposito'),
(7, 6, 0, 100, 'Retiro'),
(8, 6, 1500, 0, 'Deposito'),
(9, 11, 0, 100, 'Retiro'),
(10, 11, 2000, 0, 'Deposito'),
(11, 12, 0, 200, 'Retiro'),
(12, 12, 50, 0, 'Deposito'),
(13, 14, 2800, 0, 'Deposito'),
(14, 15, 0, 1000, 'Retiro'),
(15, 15, 4315, 0, 'Venta'),
(16, 15, 61600, 0, 'Venta'),
(17, 15, 900, 0, 'Venta'),
(18, 15, 1800, 0, 'Venta'),
(19, 15, 30, 0, 'Venta'),
(20, 15, 105, 0, 'Venta'),
(21, 15, 0, 750, 'Retiro'),
(22, 21, 5515, 0, 'Venta'),
(23, 21, 10900, 0, 'Venta'),
(24, 22, 5415, 0, 'Venta'),
(25, 22, 3700, 0, 'Venta'),
(26, 22, 4950, 0, 'Venta'),
(27, 22, 6045, 0, 'Venta'),
(28, 22, 2250, 0, 'Venta'),
(29, 22, 5600, 0, 'Venta'),
(30, 22, 200, 0, 'Venta'),
(31, 23, 1630, 0, 'Venta'),
(32, 23, 900, 0, 'Venta'),
(33, 23, 15, 0, 'Venta'),
(34, 23, 900, 0, 'Venta'),
(35, 23, 3000, 0, 'Venta'),
(36, 23, 3600, 0, 'Venta'),
(37, 23, 4400, 0, 'Venta'),
(38, 23, 1700, 0, 'Venta'),
(39, 23, 15, 0, 'Venta'),
(40, 23, 1000, 0, 'Venta'),
(41, 23, 1500, 0, 'Venta'),
(42, 23, 1650, 0, 'Venta'),
(43, 23, 8100, 0, 'Venta'),
(44, 23, 2800, 0, 'Venta'),
(45, 23, 2700, 0, 'Venta'),
(46, 23, 700, 0, 'Venta'),
(47, 23, 5100, 0, 'Venta'),
(48, 26, 0, 100, 'Retiro'),
(49, 26, 2715, 0, 'Venta'),
(50, 27, 900, 0, 'Venta'),
(51, 28, 2000, 0, 'Venta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventa`
--

CREATE TABLE `detalleventa` (
  `idDetalleVenta` int(11) NOT NULL,
  `idVenta` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioVenta` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalleventa`
--

INSERT INTO `detalleventa` (`idDetalleVenta`, `idVenta`, `idProducto`, `cantidad`, `precioVenta`) VALUES
(1, 4, 1, 1, 500),
(2, 5, 3, 4, 15),
(3, 5, 1, 1, 500),
(4, 6, 1, 1, 500),
(5, 7, 3, 10, 15),
(6, 8, 3, 4, 60),
(7, 8, 1, 1, 500),
(8, 9, 3, 4, 60),
(9, 9, 1, 1, 500),
(10, 10, 2, 3, 2700),
(11, 10, 4, 10, 1000),
(12, 12, 1, 1, 500),
(13, 12, 2, 1, 900),
(14, 12, 5, 1, 500),
(15, 12, 3, 10, 150),
(16, 13, 1, 1, 500),
(17, 13, 1, 1, 500),
(18, 13, 1, 10, 5000),
(19, 16, 1, 1, 500),
(20, 17, 1, 1, 500),
(21, 18, 1, 1, 500),
(22, 19, 1, 1, 1000),
(23, 20, 1, 1, 700),
(24, 21, 1, 1, 700),
(25, 22, 1, 1, 700),
(26, 23, 1, 1, 700),
(27, 23, 1, 1, 700),
(28, 24, 1, 1, 700),
(29, 25, 1, 1, 700),
(30, 26, 2, 2, 1800),
(31, 27, 1, 1, 700),
(32, 28, 4, 2, 200),
(33, 29, 2, 1, 900),
(34, 29, 5, 2, 1000),
(35, 30, 1, 4, 2800),
(36, 31, 1, 1, 700),
(37, 31, 2, 4, 3600),
(38, 31, 3, 1, 15),
(39, 32, 1, 88, 61600),
(40, 33, 2, 1, 900),
(41, 34, 2, 2, 1800),
(42, 35, 3, 2, 30),
(43, 36, 3, 7, 105),
(44, 37, 1, 4, 3200),
(45, 37, 6, 2, 1800),
(46, 37, 7, 2, 500),
(47, 37, 3, 1, 15),
(48, 38, 1, 8, 6400),
(49, 38, 6, 2, 1800),
(50, 38, 2, 1, 900),
(51, 38, 8, 3, 1800),
(52, 39, 2, 3, 2700),
(53, 39, 3, 1, 15),
(54, 39, 5, 1, 500),
(55, 39, 8, 1, 600),
(56, 39, 1, 2, 1600),
(57, 40, 6, 2, 1800),
(58, 40, 2, 1, 900),
(59, 40, 5, 1, 500),
(60, 40, 7, 2, 500),
(61, 41, 1, 2, 1600),
(62, 41, 4, 2, 200),
(63, 41, 6, 1, 900),
(64, 41, 7, 3, 750),
(65, 41, 5, 3, 1500),
(66, 42, 1, 2, 1600),
(67, 42, 2, 1, 900),
(68, 42, 3, 3, 45),
(69, 42, 4, 2, 200),
(70, 42, 5, 3, 1500),
(71, 42, 6, 2, 1800),
(72, 43, 7, 1, 250),
(73, 43, 6, 1, 900),
(74, 43, 8, 1, 600),
(75, 43, 5, 1, 500),
(76, 44, 1, 3, 2400),
(77, 44, 2, 2, 1800),
(78, 44, 5, 1, 500),
(79, 44, 6, 1, 900),
(80, 45, 4, 2, 200),
(81, 46, 3, 2, 30),
(82, 46, 1, 2, 1600),
(83, 46, 2, 0, 0),
(84, 47, 2, 1, 900),
(85, 48, 3, 1, 15),
(86, 49, 6, 1, 900),
(87, 50, 8, 5, 3000),
(88, 51, 5, 2, 1000),
(89, 51, 2, 2, 1800),
(90, 51, 1, 1, 800),
(91, 52, 6, 4, 3600),
(92, 52, 1, 1, 800),
(93, 53, 1, 1, 800),
(94, 53, 2, 1, 900),
(95, 54, 3, 1, 15),
(96, 55, 5, 2, 1000),
(97, 56, 8, 1, 600),
(98, 56, 1, 0, 0),
(99, 56, 2, 1, 900),
(100, 57, 2, 1, 900),
(101, 57, 5, 1, 500),
(102, 57, 7, 1, 250),
(103, 58, 2, 1, 900),
(104, 58, 6, 8, 7200),
(105, 59, 8, 3, 1800),
(106, 59, 5, 2, 1000),
(107, 60, 6, 3, 2700),
(108, 61, 8, 1, 600),
(109, 61, 4, 1, 100),
(110, 62, 1, 3, 2400),
(111, 62, 2, 3, 2700),
(112, 63, 2, 1, 900),
(113, 63, 3, 1, 15),
(114, 63, 6, 2, 1800),
(115, 64, 2, 1, 900),
(116, 65, 17, 1, 200),
(117, 65, 2, 2, 1800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flujocaja`
--

CREATE TABLE `flujocaja` (
  `idFlujoCaja` int(11) NOT NULL,
  `idCaja` int(11) NOT NULL,
  `idVendedor` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `inicial` double NOT NULL,
  `ingreso` double NOT NULL,
  `egreso` double NOT NULL,
  `total` double NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `flujocaja`
--

INSERT INTO `flujocaja` (`idFlujoCaja`, `idCaja`, `idVendedor`, `fecha`, `inicial`, `ingreso`, `egreso`, `total`, `estado`) VALUES
(6, 2, 1, '2022-12-13', 1000, 1600, 200, 1400, 0),
(11, 2, 1, '2022-12-13', 1000, 2000, 100, 1900, 0),
(12, 2, 1, '2022-12-14', 1000, 50, 200, -150, 0),
(13, 0, 1, '2022-12-14', 1000, 0, 0, 0, 0),
(14, 2, 1, '2022-12-14', 1000, 2800, 0, 2800, 0),
(15, 2, 1, '2022-12-14', 10000, 68750, 1750, 67000, 0),
(16, 2, 1, '2022-12-14', 10000, 0, 0, 0, 0),
(17, 2, 1, '2022-12-14', 1000, 0, 0, 1000, 0),
(18, 2, 6, '2022-12-20', 1000, 0, 0, 0, 0),
(19, 1, 6, '2022-12-20', 1000, 0, 0, 0, 0),
(20, 0, 6, '2022-12-20', 10, 0, 0, 10, 0),
(21, 1, 6, '2022-12-20', 1000, 16415, 0, 16415, 0),
(22, 2, 6, '2022-12-20', 10, 28160, 0, 28160, 0),
(23, 3, 1, '2022-12-20', 1000, 38080, 0, 38080, 0),
(24, 3, 1, '2022-12-21', 100, 0, 0, 100, 1),
(25, 2, 1, '2022-12-21', 100, 0, 0, 0, 0),
(26, 2, 1, '2022-12-21', 1000, 2715, 100, 3615, 1),
(27, 3, 1, '2022-12-28', 1000, 900, 0, 900, 0),
(28, 2, 1, '2022-12-28', 100, 2000, 0, 2100, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `codBarra` varchar(13) NOT NULL DEFAULT '-',
  `nombre` varchar(244) NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `categoria` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `codBarra`, `nombre`, `precio`, `stock`, `categoria`) VALUES
(1, '-', 'Carne Molida', 800, 73, 'Carniceria'),
(2, '-', 'Ayudin', 900, 18, 'Limpieza'),
(3, '-', 'Banana', 15, 14, 'Verduleria'),
(4, '-', 'Manzana', 100, 191, 'Verduleria'),
(5, '-', 'Hamburguesa', 500, 1, 'Carniceria'),
(6, '-', 'Aceite de Girasol', 900, 71, 'Comestibles'),
(7, '-', 'Arroz', 250, 91, 'Comestibles'),
(8, '-', 'Atún', 600, 85, 'Verduleria'),
(16, '-', 'Coca', 500, 10, 'Bebidas'),
(17, 'pepsi', 'pepsi', 200, 9, 'Bebidas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `idProveedor` int(11) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `razonSocial` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`idProveedor`, `dni`, `nombre`, `tel`, `razonSocial`) VALUES
(1, '43344028', 'Jese Medina', '3644222222', 'COCA'),
(3, '12345678', 'Lorenzo', '3644999999', 'PEPSI'),
(5, '345345', 'ghfgh', '345345', 'fghfgh');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `idVendedor` int(11) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `user` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`idVendedor`, `dni`, `nombre`, `tel`, `user`) VALUES
(1, '12345678', 'Vendedor X', '364444444', 'vendedor'),
(6, '66666666', 'Carlos Gardel', '3644444444', 'carlitos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(11) NOT NULL,
  `idFlujoCaja` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `numeroSerie` int(11) NOT NULL,
  `fechaVentas` date NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `idFlujoCaja`, `idCliente`, `numeroSerie`, `fechaVentas`, `monto`) VALUES
(3, 1, 0, 1, '2022-07-19', 500),
(4, 1, 0, 2, '2022-07-19', 500),
(5, 1, 0, 3, '2022-07-19', 560),
(6, 1, 0, 4, '2022-07-19', 500),
(7, 1, 0, 5, '2022-07-19', 150),
(8, 1, 0, 6, '2022-08-19', 560),
(9, 1, 0, 7, '2022-08-19', 560),
(10, 1, 0, 8, '2022-08-20', 3700),
(11, 1, 0, 9, '2022-08-21', 1060),
(12, 1, 0, 10, '2022-08-21', 2050),
(13, 1, 0, 11, '2022-09-17', 500),
(16, 0, 1, 12, '2022-10-01', 500),
(17, 0, 1, 13, '2022-10-01', 500),
(18, 0, 0, 14, '2022-10-01', 500),
(19, 0, 1, 15, '2022-12-04', 1000),
(20, 0, 0, 16, '2022-12-04', 700),
(21, 0, 0, 17, '2022-12-04', 700),
(22, 0, 1, 18, '2022-12-04', 700),
(23, 0, 0, 19, '2022-12-06', 700),
(24, 0, 0, 20, '2022-12-06', 700),
(25, 0, 0, 21, '2022-12-06', 700),
(26, 0, 0, 22, '2022-12-06', 1800),
(27, 5, 0, 23, '2022-12-08', 700),
(28, 2, 0, 24, '2022-12-08', 200),
(29, 9, 0, 25, '2022-12-08', 1900),
(30, 2, 0, 26, '2022-12-14', 2800),
(31, 2, 0, 27, '2022-12-14', 4315),
(32, 2, 0, 28, '2022-12-14', 61600),
(33, 2, 0, 29, '2022-12-14', 900),
(34, 15, 0, 30, '2022-12-14', 1800),
(35, 15, 0, 31, '2022-12-14', 30),
(36, 15, 0, 32, '2022-12-14', 105),
(37, 21, 0, 33, '2022-12-20', 5515),
(38, 21, 0, 34, '2022-12-20', 10900),
(39, 22, 0, 35, '2022-12-20', 5415),
(40, 22, 0, 36, '2022-12-20', 3700),
(41, 22, 0, 37, '2022-12-20', 4950),
(42, 22, 0, 38, '2022-12-20', 6045),
(43, 22, 0, 39, '2022-12-20', 2250),
(44, 22, 0, 40, '2022-12-20', 5600),
(45, 22, 0, 41, '2022-12-20', 200),
(46, 23, 0, 42, '2022-12-20', 1630),
(47, 23, 0, 43, '2022-12-20', 900),
(48, 23, 0, 44, '2022-12-20', 15),
(49, 23, 0, 45, '2022-12-20', 900),
(50, 23, 0, 46, '2022-12-20', 3000),
(51, 23, 0, 47, '2022-12-20', 3600),
(52, 23, 0, 48, '2022-12-20', 4400),
(53, 23, 0, 49, '2022-12-20', 1700),
(54, 23, 0, 50, '2022-12-20', 15),
(55, 23, 0, 51, '2022-12-20', 1000),
(56, 23, 0, 52, '2022-12-20', 1500),
(57, 23, 0, 53, '2022-12-20', 1650),
(58, 23, 0, 54, '2022-12-20', 8100),
(59, 23, 0, 55, '2022-12-20', 2800),
(60, 23, 0, 56, '2022-12-20', 2700),
(61, 23, 0, 57, '2022-12-20', 700),
(62, 23, 0, 58, '2022-12-20', 5100),
(63, 26, 1, 59, '2022-12-21', 2715),
(64, 27, 0, 60, '2022-12-28', 900),
(65, 28, 0, 61, '2022-12-28', 2000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAdmin`);

--
-- Indices de la tabla `caja`
--
ALTER TABLE `caja`
  ADD PRIMARY KEY (`idCaja`),
  ADD UNIQUE KEY `numeroCaja` (`numeroCaja`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`idCompra`),
  ADD KEY `idProveedor` (`idProveedor`);

--
-- Indices de la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD PRIMARY KEY (`idDetalleCompra`),
  ADD KEY `idCompra` (`idCompra`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `detalleflujocaja`
--
ALTER TABLE `detalleflujocaja`
  ADD PRIMARY KEY (`idDetalleFlujoCaja`),
  ADD KEY `idFlujoCaja` (`idFlujoCaja`);

--
-- Indices de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD PRIMARY KEY (`idDetalleVenta`),
  ADD KEY `idVenta` (`idVenta`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `flujocaja`
--
ALTER TABLE `flujocaja`
  ADD PRIMARY KEY (`idFlujoCaja`),
  ADD KEY `idCaja` (`idCaja`),
  ADD KEY `idVendedor` (`idVendedor`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`idProveedor`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`idVendedor`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idVenta`),
  ADD KEY `idCliente` (`idCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admin`
  MODIFY `idAdmin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `caja`
--
ALTER TABLE `caja`
  MODIFY `idCaja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `idCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  MODIFY `idDetalleCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `detalleflujocaja`
--
ALTER TABLE `detalleflujocaja`
  MODIFY `idDetalleFlujoCaja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  MODIFY `idDetalleVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT de la tabla `flujocaja`
--
ALTER TABLE `flujocaja`
  MODIFY `idFlujoCaja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `idProveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `idVendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD CONSTRAINT `detallecompra_ibfk_3` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detallecompra_ibfk_4` FOREIGN KEY (`idCompra`) REFERENCES `compras` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD CONSTRAINT `detalleventa_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalleventa_ibfk_2` FOREIGN KEY (`idVenta`) REFERENCES `ventas` (`idVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
