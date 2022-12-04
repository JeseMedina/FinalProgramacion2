-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-12-2022 a las 13:14:54
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
(7, 'Gaseosas');

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
(3, '12345678', 'rasr', '3644222222');

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
(15, 1, 4, '2022-09-17', 82830);

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
(1, 13, 3, 8, 7200),
(2, 14, 2, 10, 5000),
(3, 15, 2, 100, 50000),
(4, 15, 3, 10, 9000),
(5, 15, 4, 2, 30),
(6, 15, 7, 198, 19800),
(7, 15, 8, 8, 4000);

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
(1, 4, 2, 1, 500),
(2, 5, 4, 4, 15),
(3, 5, 2, 1, 500),
(4, 6, 2, 1, 500),
(5, 7, 4, 10, 15),
(6, 8, 4, 4, 60),
(7, 8, 2, 1, 500),
(8, 9, 4, 4, 60),
(9, 9, 2, 1, 500),
(10, 10, 3, 3, 2700),
(11, 10, 7, 10, 1000),
(12, 12, 2, 1, 500),
(13, 12, 3, 1, 900),
(14, 12, 8, 1, 500),
(15, 12, 4, 10, 150),
(16, 13, 2, 1, 500),
(17, 13, 2, 1, 500),
(18, 13, 2, 10, 5000),
(19, 16, 2, 1, 500),
(20, 17, 2, 1, 500),
(21, 18, 2, 1, 500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `nombre` varchar(244) NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `categoria` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `nombre`, `precio`, `stock`, `categoria`) VALUES
(2, 'Carne Molida', 500, 116, 'Carniceria'),
(3, 'Ayudin', 900, 40, 'Limpieza'),
(4, 'Banana', 15, 34, 'Verduleria'),
(7, 'Manzana', 100, 200, 'Verduleria'),
(8, 'Hamburguesa', 500, 20, 'Carniceria');

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
(3, '12345678', 'Lorenzo', '3644999999', 'PEPSI');

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
(1, '12345678', 'Jese Medin', '364444444', 'jese'),
(5, '87654321', 'Jose Castelar', '3644444444', 'jose');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(11) NOT NULL,
  `idVendedor` int(11) NOT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `numeroSerie` int(11) NOT NULL,
  `fechaVentas` date NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `idVendedor`, `idCliente`, `numeroSerie`, `fechaVentas`, `monto`) VALUES
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
(18, 0, 0, 14, '2022-10-01', 500);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAdmin`);

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
-- Indices de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD PRIMARY KEY (`idDetalleVenta`),
  ADD KEY `idVenta` (`idVenta`),
  ADD KEY `idProducto` (`idProducto`);

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
  ADD KEY `idVendedor` (`idVendedor`),
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
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `idCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  MODIFY `idDetalleCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  MODIFY `idDetalleVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `idProveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `idVendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

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
