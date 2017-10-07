-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 07, 2017 at 06:23 AM
-- Server version: 10.1.24-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id3109896_mandi`
--

-- --------------------------------------------------------

--
-- Table structure for table `commodities`
--

CREATE TABLE `commodities` (
  `comid` int(50) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `price` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `picid` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `commodities`
--

INSERT INTO `commodities` (`comid`, `name`, `price`, `picid`) VALUES
(1, 'potato', '20', '1'),
(2, 'tomato', '40', '2'),
(3, 'lady finger', '15', '3'),
(4, 'carrot', '20', '4'),
(5, 'cauliflower', '25', '5'),
(6, 'brinjal', '30', '6'),
(7, 'onion', '40', '7'),
(8, 'peas', '25', '8');

-- --------------------------------------------------------

--
-- Table structure for table `equiprequests`
--

CREATE TABLE `equiprequests` (
  `equipid` int(50) NOT NULL,
  `equipments` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `userid` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `labourers`
--

CREATE TABLE `labourers` (
  `labid` int(50) NOT NULL,
  `numlabourers` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `userid` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` int(50) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pin` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `district` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tehsil` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `village` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ismale` int(1) NOT NULL,
  `isfarmer` int(1) NOT NULL,
  `size` int(1) NOT NULL,
  `crops` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `coverpic` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `regid` varchar(250) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `name`, `phone`, `pass`, `pin`, `state`, `district`, `tehsil`, `village`, `ismale`, `isfarmer`, `size`, `crops`, `coverpic`, `regid`) VALUES
(1, 'shivam', '9045186708', '3ae9d8799d1bb5e201e5704293bb54ef', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', 'pantnagar', 1, 1, 0, '', '', ''),
(2, 'djjd', '9632580774', 'df6d2338b2b8fce1ec2f6dda0a630eb0', '', '', '', '', '', 1, 1, 0, '', '', ''),
(3, 'ugu', '9632580741', '8e296a067a37563370ded05f5a3bf3ec', '', '', '', '', '', 1, 1, 0, '', '', ''),
(4, 'vi', '9632580778', '502e4a16930e414107ee22b6198c578f', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', '', 0, 0, 0, '', '', ''),
(5, 'ufifi', '7896541230', '8e296a067a37563370ded05f5a3bf3ec', '', '', '', '', '', 1, 1, 0, '', '', ''),
(6, 'jfjf', '1470852369', '05a3699379b0a4f50cc0bb28e888ba52', '', '', '', '', '', 1, 1, 0, '', '', ''),
(7, 'ufvu', '6541239870', 'f718499c1c8cef6730f9fd03c8125cab', '', '', '', '', '', 1, 1, 0, '', '', ''),
(8, 'kgj', '3699085236', 'fc490ca45c00b1249bbe3554a4fdf6fb', '', '', '', '', '', 1, 1, 0, '', '', ''),
(9, 'fuu', '1478852369', 'a16ffb344f7da08cf7d62e80d1df097c', '', '', '', '', '', 1, 1, 0, '', '', ''),
(10, 'jcj', '3256987410', 'c81e728d9d4c2f636f067f89cc14862c', '', '', '', '', '', 1, 1, 0, '', '', ''),
(11, 'gg', '9586589652', '8e296a067a37563370ded05f5a3bf3ec', '', '', '', '', '', 1, 1, 1, '{\"0\":\"Wheat\",\"1\":\"Sugarcane\"}', '1506944133691', ''),
(12, 'hdhd', '3214569870', '8e296a067a37563370ded05f5a3bf3ec', '', '', '', '', '', 1, 1, 2, '{\"0\":\"Wheat\",\"1\":\"Sugarcane\",\"2\":\"Patato\"}', '1506945951346', ''),
(13, 'utnm', '6695695663', 'b97f525349ba8bdfed7813dcbc869ab0', '247667', 'UTTARAKHAND', 'Haridwar', 'NA', 'ghju', 1, 1, 0, '', '', ''),
(14, 'vi', '9632580741', '379ab0615d0a2f2efc9a82340d0d706b', '', '', '', '', '', 1, 1, 0, '', '', ''),
(15, 'djhdh', '5959959654', '72c14df7338e72a7d88f0923a4617cda', '', '', '', '', '', 1, 1, 0, '', '', ''),
(16, 'djhdh', '5959959655', '72c14df7338e72a7d88f0923a4617cda', '', '', '', '', '', 1, 0, 0, '', '', ''),
(17, 'pabsh', '9999999999', '00cedcf91beffa9ee69f6cfe23a4602d', '', 'utta', '', '', '', 0, 0, 0, '', '', ''),
(18, 'usjw', '9494949494', '5bf73bc6c6e6775d472621264309a88b', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', '', 1, 1, 0, '', '', ''),
(19, 'usjw', '9494949494', '5bf73bc6c6e6775d472621264309a88b', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', '', 1, 1, 0, '{\"0\":\"Wheat\",\"1\":\"Rice\",\"2\":\"Jaggery\",\"3\":\"Patato\"', 'none', ''),
(20, 'shivam', '9652314780', '25d55ad283aa400af464c76d713c07ad', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', '', 1, 1, 0, '', '', ''),
(21, 'jfu', '2589631470', 'dbd22ba3bd0df8f385bdac3e9f8be207', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', 'jvjv', 0, 0, 0, '', '', ''),
(22, 'duf', '2580963147', 'c855f26732ea0e86fda6157f4aca2fcc', '', '', '', '', '', 1, 1, 0, '', '', ''),
(23, 'duf', '2580963145', 'c855f26732ea0e86fda6157f4aca2fcc', '', '', '', '', '', 1, 1, 0, '', '', ''),
(24, 'duf', '2580963144', 'c855f26732ea0e86fda6157f4aca2fcc', '', '', '', '', '', 1, 1, 0, '{\"0\":\"Sugarcane\",\"1\":\"Wheat\"}', 'none', ''),
(25, 'hshaja', '6464646645', 'fd2abef4ec26ec43bde71585c1e3f7b3', '788151', 'ASSAM', 'Cachar', 'NA', '', 1, 1, 0, '{\"0\":\"Rice\",\"1\":\"Wheat\"}', 'none', ''),
(26, 'bib', '1470852369', '3295c76acbf4caaed33c36b1b5fc2cb1', '', '', '', '', '', 1, 1, 0, '{\"0\":\"Wheat\",\"1\":\"Sugarcane\"}', 'none', ''),
(27, 'bsbe', '9870654123', '19ca14e7ea6328a42e0eb13d585e4c22', '', '', '', '', '', 1, 1, 1, '{\"0\":\"Carrot\"}', 'none', ''),
(28, 'fjfu', '1470852369', '0c74b7f78409a4022a2c4c5a5ca3ee19', '', '', '', '', '', 1, 1, 0, '{\"0\":\"Cabbage\"}', 'none', ''),
(29, 'igigi', '5412369870', '8e296a067a37563370ded05f5a3bf3ec', '263152', 'UTTARAKHAND', 'Udham Singh Nagar', 'Gadarpur S.O', 'vivio', 1, 1, 0, '{\"0\":\"Carrot\",\"1\":\"Broccoli\"}', 'none', ''),
(30, 'dhhd', '6325418790', 'd9d4f495e875a2e075a1a4a6e1b9770f', '272153', 'UTTAR PRADESH', 'Siddharthnagar', 'NA', '', 1, 1, 0, '{\"0\":\"Carrot\",\"1\":\"Cabbage\"}', 'none', ''),
(31, 'kviv', '1247856935', 'a9a1d5317a33ae8cef33961c34144f84', '263153', 'UTTARAKHAND', 'Udham Singh Nagar', 'NA', '', 1, 0, 0, '', '', ''),
(32, 'Rahul sharma', '9865784564', '2dbf21633f03afcf882eaf10e4b5caca', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', 'pantnagar', 1, 1, 0, '', '', ''),
(33, 'hcjf', '6908523147', '2bb232c0b13c774965ef8558f0fbd615', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', 'cjcjvj', 1, 1, 0, '{\"0\":\"Tomato\",\"1\":\"Onion\"}', 'none', ''),
(34, ' hc', '6325980741', '19ca14e7ea6328a42e0eb13d585e4c22', '263152', 'UTTARAKHAND', 'Udham Singh Nagar', 'Gadarpur S.O', '', 1, 1, 0, '{\"0\":\"Carrot\",\"1\":\"Broccoli\"}', 'none', ''),
(35, 'bubib', '9632588741', '33e75ff09dd601bbe69f351039152189', '263152', 'UTTARAKHAND', 'Udham Singh Nagar', 'Gadarpur S.O', '', 1, 0, 0, '', '', ''),
(36, 'sh', '1234567890', 'c52f1bd66cc19d05628bd8bf27af3ad6', '263152', 'UTTARAKHAND', 'Udham Singh Nagar', 'Gadarpur S.O', '', 1, 1, 0, '{\"0\":\"Carrot\",\"1\":\"Cabbage\"}', 'none', ''),
(37, 'gg', '2541369870', '9a1158154dfa42caddbd0694a4e9bdc8', '263145', 'UTTARAKHAND', 'Udham Singh Nagar', 'Pantnagar S.O', '', 1, 1, 0, '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commodities`
--
ALTER TABLE `commodities`
  ADD PRIMARY KEY (`comid`);

--
-- Indexes for table `equiprequests`
--
ALTER TABLE `equiprequests`
  ADD PRIMARY KEY (`equipid`);

--
-- Indexes for table `labourers`
--
ALTER TABLE `labourers`
  ADD PRIMARY KEY (`labid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commodities`
--
ALTER TABLE `commodities`
  MODIFY `comid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `equiprequests`
--
ALTER TABLE `equiprequests`
  MODIFY `equipid` int(50) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `labourers`
--
ALTER TABLE `labourers`
  MODIFY `labid` int(50) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
