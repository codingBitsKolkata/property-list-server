-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2018 at 05:30 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ora_property`
--

-- --------------------------------------------------------

--
-- Table structure for table `master_accommodation`
--

DROP TABLE IF EXISTS `master_accommodation`;
CREATE TABLE IF NOT EXISTS `master_accommodation` (
  `accommodation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `accommodation_name` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`accommodation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_accommodation`
--

INSERT INTO `master_accommodation` (`accommodation_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `accommodation_name`, `language_id`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Shared', 1, 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Private', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_amenities`
--

DROP TABLE IF EXISTS `master_amenities`;
CREATE TABLE IF NOT EXISTS `master_amenities` (
  `aminities_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `aminities_name` varchar(255) DEFAULT NULL,
  `filter_flag` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `aminities_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`aminities_id`),
  KEY `FKp3o29x7c2yypk58qqciptjj8a` (`aminities_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_amenities`
--

INSERT INTO `master_amenities` (`aminities_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `aminities_name`, `filter_flag`, `language_id`, `parent_id`, `priority`, `aminities_type_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'WIFI', '1', 1, 0, '1', 1),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Heater', '1', 1, 0, '2', 1),
(3, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Table Lamp', '1', 1, 0, '3', 2);

-- --------------------------------------------------------

--
-- Table structure for table `master_amenities_type`
--

DROP TABLE IF EXISTS `master_amenities_type`;
CREATE TABLE IF NOT EXISTS `master_amenities_type` (
  `aminities_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `aminities_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aminities_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_amenities_type`
--

INSERT INTO `master_amenities_type` (`aminities_type_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `aminities_type_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Basic'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Others');

-- --------------------------------------------------------

--
-- Table structure for table `master_cancellation_slab`
--

DROP TABLE IF EXISTS `master_cancellation_slab`;
CREATE TABLE IF NOT EXISTS `master_cancellation_slab` (
  `cancellation_slab_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cancellation_slab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_cancellation_slab`
--

INSERT INTO `master_cancellation_slab` (`cancellation_slab_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `end_time`, `start_time`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '0', '24'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '24', '36');

-- --------------------------------------------------------

--
-- Table structure for table `master_discount_category_host`
--

DROP TABLE IF EXISTS `master_discount_category_host`;
CREATE TABLE IF NOT EXISTS `master_discount_category_host` (
  `dch_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dis_cat_host_name` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_discount_category_host`
--

INSERT INTO `master_discount_category_host` (`dch_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `dis_cat_host_name`, `language_id`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Weekly', 1, 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Monthly', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_discount_category_ora`
--

DROP TABLE IF EXISTS `master_discount_category_ora`;
CREATE TABLE IF NOT EXISTS `master_discount_category_ora` (
  `dco_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dis_cat_ora_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_discount_category_ora`
--

INSERT INTO `master_discount_category_ora` (`dco_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `dis_cat_ora_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Percentage'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Price');

-- --------------------------------------------------------

--
-- Table structure for table `master_meal_category`
--

DROP TABLE IF EXISTS `master_meal_category`;
CREATE TABLE IF NOT EXISTS `master_meal_category` (
  `meal_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `meal_category_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`meal_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_meal_category`
--

INSERT INTO `master_meal_category` (`meal_category_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `meal_category_name`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Normal', 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Daily', 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_meal_days`
--

DROP TABLE IF EXISTS `master_meal_days`;
CREATE TABLE IF NOT EXISTS `master_meal_days` (
  `meal_days_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`meal_days_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_meal_days`
--

INSERT INTO `master_meal_days` (`meal_days_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `name`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Sunday', 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Monday', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `master_meal_plan`
--

DROP TABLE IF EXISTS `master_meal_plan`;
CREATE TABLE IF NOT EXISTS `master_meal_plan` (
  `meal_plan_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `meal_plan_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`meal_plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_meal_plan`
--

INSERT INTO `master_meal_plan` (`meal_plan_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `meal_plan_name`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Breakfast', 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Major Meal1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_meal_plan_category`
--

DROP TABLE IF EXISTS `master_meal_plan_category`;
CREATE TABLE IF NOT EXISTS `master_meal_plan_category` (
  `mpc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `mpc_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mpc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_meal_plan_category`
--

INSERT INTO `master_meal_plan_category` (`mpc_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `mpc_name`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'AP', 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'CP', 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_meal_price_category`
--

DROP TABLE IF EXISTS `master_meal_price_category`;
CREATE TABLE IF NOT EXISTS `master_meal_price_category` (
  `mmpc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mmpc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_meal_price_category`
--

INSERT INTO `master_meal_price_category` (`mmpc_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `name`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Free', 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Chargable', 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_meal_type`
--

DROP TABLE IF EXISTS `master_meal_type`;
CREATE TABLE IF NOT EXISTS `master_meal_type` (
  `meal_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`meal_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_meal_type`
--

INSERT INTO `master_meal_type` (`meal_type_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `img_url`, `name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'google.com', 'veg'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'google.com', 'Non Veg');

-- --------------------------------------------------------

--
-- Table structure for table `master_pg_category_sex`
--

DROP TABLE IF EXISTS `master_pg_category_sex`;
CREATE TABLE IF NOT EXISTS `master_pg_category_sex` (
  `pgcs_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pgcs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_pg_category_sex`
--

INSERT INTO `master_pg_category_sex` (`pgcs_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `category_name`, `language_id`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Male', 1, 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Female', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_price_drop`
--

DROP TABLE IF EXISTS `master_price_drop`;
CREATE TABLE IF NOT EXISTS `master_price_drop` (
  `price_drop_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `after_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`price_drop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_price_drop`
--

INSERT INTO `master_price_drop` (`price_drop_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `after_time`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '8'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '11');

-- --------------------------------------------------------

--
-- Table structure for table `master_price_type`
--

DROP TABLE IF EXISTS `master_price_type`;
CREATE TABLE IF NOT EXISTS `master_price_type` (
  `price_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `price_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`price_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_price_type`
--

INSERT INTO `master_price_type` (`price_type_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `parent_id`, `price_type_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 0, 'Base Price'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, NULL, 1, 0, 'Convenience Fee');

-- --------------------------------------------------------

--
-- Table structure for table `master_property`
--

DROP TABLE IF EXISTS `master_property`;
CREATE TABLE IF NOT EXISTS `master_property` (
  `property_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `apartment_name` varchar(255) DEFAULT NULL,
  `apartment_number` varchar(255) DEFAULT NULL,
  `checkin_time` varchar(255) DEFAULT NULL,
  `checkout_time` varchar(255) DEFAULT NULL,
  `cover_image_url` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `entire_apartment` varchar(255) DEFAULT NULL,
  `immediate_booking` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `oraname` varchar(255) DEFAULT NULL,
  `price_drop` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `strict_checkin` varchar(255) DEFAULT NULL,
  `pgcs_id` bigint(20) NOT NULL,
  `property_type_id` bigint(20) NOT NULL,
  `stay_type_id` bigint(20) NOT NULL,
  `user_vs_account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `FKdbbufp6ie2vuvbpe1yrmhi07i` (`pgcs_id`),
  KEY `FK5a00uodiowpit4s2fbuqgbm09` (`property_type_id`),
  KEY `FK156vy9s4sl5mh6nxjo74bg2rv` (`stay_type_id`),
  KEY `FK7ut9f4l3mjekant5shjos5dts` (`user_vs_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_property_type`
--

DROP TABLE IF EXISTS `master_property_type`;
CREATE TABLE IF NOT EXISTS `master_property_type` (
  `property_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`property_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_property_type`
--

INSERT INTO `master_property_type` (`property_type_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `name`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Homestay', 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Hotel', 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_room`
--

DROP TABLE IF EXISTS `master_room`;
CREATE TABLE IF NOT EXISTS `master_room` (
  `user_vs_document_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `document_number` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cot_available` varchar(255) DEFAULT NULL,
  `floor_no` varchar(255) DEFAULT NULL,
  `no_of_child` varchar(255) DEFAULT NULL,
  `no_of_guest` varchar(255) DEFAULT NULL,
  `num_of_cot` varchar(255) DEFAULT NULL,
  `shared_space` varchar(255) DEFAULT NULL,
  `document_id` bigint(20) NOT NULL,
  `property_id` bigint(20) NOT NULL,
  `accommodation_id` bigint(20) NOT NULL,
  `room_cat_id` bigint(20) NOT NULL,
  `room_standard_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FKea5fxfdj6pc2f02cdhgf5moly` (`document_id`),
  KEY `FK44tv0dyh1xvs2elrr7yr3tr1x` (`property_id`),
  KEY `FK1jg5ov7jt5k4iaivxxa58u891` (`accommodation_id`),
  KEY `FKjw1vho56i8n32d1tyt64yh8ey` (`room_cat_id`),
  KEY `FKk76nhy20o1xpxxvciuyo75c93` (`room_standard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_room_category`
--

DROP TABLE IF EXISTS `master_room_category`;
CREATE TABLE IF NOT EXISTS `master_room_category` (
  `room_cat_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `property_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_cat_id`),
  KEY `FKljipnikicv5k5pfpao2dflhpj` (`property_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_room_category`
--

INSERT INTO `master_room_category` (`room_cat_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `name`, `parent_id`, `property_type_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Stansard', 0, 1),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Delux', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `master_room_standard`
--

DROP TABLE IF EXISTS `master_room_standard`;
CREATE TABLE IF NOT EXISTS `master_room_standard` (
  `room_standard_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`room_standard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_room_standard`
--

INSERT INTO `master_room_standard` (`room_standard_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `name`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Express', 0),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 'Premium', 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_space_rule`
--

DROP TABLE IF EXISTS `master_space_rule`;
CREATE TABLE IF NOT EXISTS `master_space_rule` (
  `sprule_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `rule_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sprule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_space_rule`
--

INSERT INTO `master_space_rule` (`sprule_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `parent_id`, `rule_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 0, 'Smoking'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 0, 'Drinking');

-- --------------------------------------------------------

--
-- Table structure for table `master_specialties`
--

DROP TABLE IF EXISTS `master_specialties`;
CREATE TABLE IF NOT EXISTS `master_specialties` (
  `specialties_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`specialties_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_specialties`
--

INSERT INTO `master_specialties` (`specialties_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `parent_id`, `name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 0, 'Mountain Facing'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 0, 'Sea Facing');

-- --------------------------------------------------------

--
-- Table structure for table `master_special_experience`
--

DROP TABLE IF EXISTS `master_special_experience`;
CREATE TABLE IF NOT EXISTS `master_special_experience` (
  `experience_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `experience_name` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`experience_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_special_experience`
--

INSERT INTO `master_special_experience` (`experience_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `experience_name`, `language_id`, `parent_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Musical Instrument', 1, 0),
(2, 1, '2018-09-06 01:27:34', 1, NULL, 1, 'Yoga Centre', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `master_stay_type`
--

DROP TABLE IF EXISTS `master_stay_type`;
CREATE TABLE IF NOT EXISTS `master_stay_type` (
  `stay_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `stay_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stay_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_stay_type`
--

INSERT INTO `master_stay_type` (`stay_type_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `language_id`, `parent_id`, `stay_type_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 0, 'Long Term'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 0, 'Short Term');

-- --------------------------------------------------------

--
-- Table structure for table `master_wishlist`
--

DROP TABLE IF EXISTS `master_wishlist`;
CREATE TABLE IF NOT EXISTS `master_wishlist` (
  `wishlist_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  PRIMARY KEY (`wishlist_id`),
  KEY `FKqp3nmgc19tiysytctn29jkypy` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `meal_plan_cat_vs_meal_plan`
--

DROP TABLE IF EXISTS `meal_plan_cat_vs_meal_plan`;
CREATE TABLE IF NOT EXISTS `meal_plan_cat_vs_meal_plan` (
  `mpcmp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `mpc_id` bigint(20) NOT NULL,
  `meal_plan_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mpcmp_id`),
  KEY `FKiqhjoqkinxg3b872k7m68xq13` (`mpc_id`),
  KEY `FKeuyapssqoidbnljl9l9lbq78u` (`meal_plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_plan_cat_vs_meal_plan`
--

INSERT INTO `meal_plan_cat_vs_meal_plan` (`mpcmp_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `mpc_id`, `meal_plan_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 1),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `property_vs_description`
--

DROP TABLE IF EXISTS `property_vs_description`;
CREATE TABLE IF NOT EXISTS `property_vs_description` (
  `property_desc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_desc_id`),
  KEY `FK53h3qarlglrohvb624pf128m2` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property_vs_experience`
--

DROP TABLE IF EXISTS `property_vs_experience`;
CREATE TABLE IF NOT EXISTS `property_vs_experience` (
  `property_exp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  `experience_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_exp_id`),
  KEY `FK92o376xk6r7gls0he1aaugt7x` (`property_id`),
  KEY `FKn1s82f6oy0l1qh4mtmrdvjpx4` (`experience_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property_vs_guest_access`
--

DROP TABLE IF EXISTS `property_vs_guest_access`;
CREATE TABLE IF NOT EXISTS `property_vs_guest_access` (
  `property_gaccess_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `guest_access` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_gaccess_id`),
  KEY `FKg443u2hdb4sbrrnjwsrsxh7rk` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property_vs_image`
--

DROP TABLE IF EXISTS `property_vs_image`;
CREATE TABLE IF NOT EXISTS `property_vs_image` (
  `property_image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_image_id`),
  KEY `FKdrv8bc11h2o2m8uumykk4cu57` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property_vs_nearby`
--

DROP TABLE IF EXISTS `property_vs_nearby`;
CREATE TABLE IF NOT EXISTS `property_vs_nearby` (
  `property_nearby_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `places` varchar(255) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_nearby_id`),
  KEY `FKru2h6pp77q418s5973c7125de` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property_vs_pricedrop`
--

DROP TABLE IF EXISTS `property_vs_pricedrop`;
CREATE TABLE IF NOT EXISTS `property_vs_pricedrop` (
  `property_pricedrop_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `percentage` varchar(255) DEFAULT NULL,
  `price_drop_id` bigint(20) NOT NULL,
  `property_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_pricedrop_id`),
  KEY `FKnna1ewhlvt6hf3dmqx3rwd91k` (`price_drop_id`),
  KEY `FKgwycvdturw8svnhj0pveb83bg` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property_vs_space`
--

DROP TABLE IF EXISTS `property_vs_space`;
CREATE TABLE IF NOT EXISTS `property_vs_space` (
  `property_space_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  `sprule_id` bigint(20) NOT NULL,
  PRIMARY KEY (`property_space_id`),
  KEY `FKklkdybjqkwqcsydd52tcjt4g0` (`property_id`),
  KEY `FKhd1vunghcip4rtk0511i53tf7` (`sprule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_amenities`
--

DROP TABLE IF EXISTS `room_vs_amenities`;
CREATE TABLE IF NOT EXISTS `room_vs_amenities` (
  `room_vs_aminities_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `aminities_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_vs_aminities_id`),
  KEY `FKm50c7up4jp1m0vl36325pktvw` (`aminities_id`),
  KEY `FKblve2wd06ghlbt72q1onnu1ib` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_bed`
--

DROP TABLE IF EXISTS `room_vs_bed`;
CREATE TABLE IF NOT EXISTS `room_vs_bed` (
  `rb_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `no_of_beds` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rb_id`),
  KEY `FKhtr9fn5ifxsl6t6v6nvx3p7ee` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_cancellation`
--

DROP TABLE IF EXISTS `room_vs_cancellation`;
CREATE TABLE IF NOT EXISTS `room_vs_cancellation` (
  `rc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `percentage` varchar(255) DEFAULT NULL,
  `cancellation_slab_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rc_id`),
  KEY `FKr3pyo2gqwl71s8k5sm32rbjha` (`cancellation_slab_id`),
  KEY `FKemhbtji1nvg41xa261axnd7vr` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_host_discount`
--

DROP TABLE IF EXISTS `room_vs_host_discount`;
CREATE TABLE IF NOT EXISTS `room_vs_host_discount` (
  `rhd_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `percentage` varchar(255) DEFAULT NULL,
  `dch_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rhd_id`),
  KEY `FK5sclwsu8pkvrqak6mcnxoxk6v` (`dch_id`),
  KEY `FKq6d1mtvan7b7k0w3vm9m8wpti` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_image`
--

DROP TABLE IF EXISTS `room_vs_image`;
CREATE TABLE IF NOT EXISTS `room_vs_image` (
  `room_vs_image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_vs_image_id`),
  KEY `FKpamxay6j07bxeh97i0753dh6t` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_info`
--

DROP TABLE IF EXISTS `room_vs_info`;
CREATE TABLE IF NOT EXISTS `room_vs_info` (
  `ri_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `room_name` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ri_id`),
  KEY `FKdfb5k4oyqalu77f4016j5toea` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_meal`
--

DROP TABLE IF EXISTS `room_vs_meal`;
CREATE TABLE IF NOT EXISTS `room_vs_meal` (
  `room_vs_meal_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `meal_category_id` bigint(20) NOT NULL,
  `meal_days_id` bigint(20) NOT NULL,
  `mpcmp_id` bigint(20) NOT NULL,
  `mmpc_id` bigint(20) NOT NULL,
  `meal_type_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_vs_meal_id`),
  KEY `FKgtj41ducucdx7bqgr4f32ky05` (`meal_category_id`),
  KEY `FK2qaygsqn2ca5x11fjhd0l5lxi` (`meal_days_id`),
  KEY `FKq4sl9jriseumcbmun7gvuvgkb` (`mpcmp_id`),
  KEY `FKcpj8fovohx31bspss3xpi95sb` (`mmpc_id`),
  KEY `FKbwi7n73rr4g5ulx0i655j4jbw` (`meal_type_id`),
  KEY `FKbv9gqnudk5swt4bpm37f5p28b` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_ora_discount`
--

DROP TABLE IF EXISTS `room_vs_ora_discount`;
CREATE TABLE IF NOT EXISTS `room_vs_ora_discount` (
  `rod_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `discount` varchar(255) DEFAULT NULL,
  `dco_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rod_id`),
  KEY `FKpct47x10hg0lpqb40of2uqjye` (`dco_id`),
  KEY `FKk39qeirk6ipubv3iu87ehx7m4` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_ora_price`
--

DROP TABLE IF EXISTS `room_vs_ora_price`;
CREATE TABLE IF NOT EXISTS `room_vs_ora_price` (
  `rop_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `percentage` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rop_id`),
  KEY `FK2mntqtwkqsqacajio6oi1y2kv` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_price`
--

DROP TABLE IF EXISTS `room_vs_price`;
CREATE TABLE IF NOT EXISTS `room_vs_price` (
  `room_vs_price_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `price_type_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_vs_price_id`),
  KEY `FKea9eolxd563uccrp3pcq8shl7` (`price_type_id`),
  KEY `FKiy1i3cks8ywbk5saucjrltpcc` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_vs_specialties`
--

DROP TABLE IF EXISTS `room_vs_specialties`;
CREATE TABLE IF NOT EXISTS `room_vs_specialties` (
  `roomspec_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL,
  `specialties_id` bigint(20) NOT NULL,
  PRIMARY KEY (`roomspec_id`),
  KEY `FKmjiqftc5rdrthmteghtchnkav` (`room_id`),
  KEY `FKoe11vqm2reg89jso2t6d4mm7d` (`specialties_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_vs_account`
--

DROP TABLE IF EXISTS `user_vs_account`;
CREATE TABLE IF NOT EXISTS `user_vs_account` (
  `document_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `user_vs_account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_holder_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_vs_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `master_amenities`
--
ALTER TABLE `master_amenities`
  ADD CONSTRAINT `FKp3o29x7c2yypk58qqciptjj8a` FOREIGN KEY (`aminities_type_id`) REFERENCES `master_amenities_type` (`aminities_type_id`);

--
-- Constraints for table `master_property`
--
ALTER TABLE `master_property`
  ADD CONSTRAINT `FK156vy9s4sl5mh6nxjo74bg2rv` FOREIGN KEY (`stay_type_id`) REFERENCES `master_stay_type` (`stay_type_id`),
  ADD CONSTRAINT `FK5a00uodiowpit4s2fbuqgbm09` FOREIGN KEY (`property_type_id`) REFERENCES `master_property_type` (`property_type_id`),
  ADD CONSTRAINT `FK7ut9f4l3mjekant5shjos5dts` FOREIGN KEY (`user_vs_account_id`) REFERENCES `user_vs_account` (`user_vs_account_id`),
  ADD CONSTRAINT `FKdbbufp6ie2vuvbpe1yrmhi07i` FOREIGN KEY (`pgcs_id`) REFERENCES `master_pg_category_sex` (`pgcs_id`);

--
-- Constraints for table `master_room`
--
ALTER TABLE `master_room`
  ADD CONSTRAINT `FK1jg5ov7jt5k4iaivxxa58u891` FOREIGN KEY (`accommodation_id`) REFERENCES `master_accommodation` (`accommodation_id`),
  ADD CONSTRAINT `FK44tv0dyh1xvs2elrr7yr3tr1x` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`),
  ADD CONSTRAINT `FKea5fxfdj6pc2f02cdhgf5moly` FOREIGN KEY (`document_id`) REFERENCES `user_vs_account` (`user_vs_account_id`),
  ADD CONSTRAINT `FKjw1vho56i8n32d1tyt64yh8ey` FOREIGN KEY (`room_cat_id`) REFERENCES `master_room_category` (`room_cat_id`),
  ADD CONSTRAINT `FKk76nhy20o1xpxxvciuyo75c93` FOREIGN KEY (`room_standard_id`) REFERENCES `master_room_standard` (`room_standard_id`);

--
-- Constraints for table `master_room_category`
--
ALTER TABLE `master_room_category`
  ADD CONSTRAINT `FKljipnikicv5k5pfpao2dflhpj` FOREIGN KEY (`property_type_id`) REFERENCES `master_property_type` (`property_type_id`);

--
-- Constraints for table `master_wishlist`
--
ALTER TABLE `master_wishlist`
  ADD CONSTRAINT `FKqp3nmgc19tiysytctn29jkypy` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`);

--
-- Constraints for table `meal_plan_cat_vs_meal_plan`
--
ALTER TABLE `meal_plan_cat_vs_meal_plan`
  ADD CONSTRAINT `FKeuyapssqoidbnljl9l9lbq78u` FOREIGN KEY (`meal_plan_id`) REFERENCES `master_meal_plan` (`meal_plan_id`),
  ADD CONSTRAINT `FKiqhjoqkinxg3b872k7m68xq13` FOREIGN KEY (`mpc_id`) REFERENCES `master_meal_plan_category` (`mpc_id`);

--
-- Constraints for table `property_vs_description`
--
ALTER TABLE `property_vs_description`
  ADD CONSTRAINT `FK53h3qarlglrohvb624pf128m2` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`);

--
-- Constraints for table `property_vs_experience`
--
ALTER TABLE `property_vs_experience`
  ADD CONSTRAINT `FK92o376xk6r7gls0he1aaugt7x` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`),
  ADD CONSTRAINT `FKn1s82f6oy0l1qh4mtmrdvjpx4` FOREIGN KEY (`experience_id`) REFERENCES `master_special_experience` (`experience_id`);

--
-- Constraints for table `property_vs_guest_access`
--
ALTER TABLE `property_vs_guest_access`
  ADD CONSTRAINT `FKg443u2hdb4sbrrnjwsrsxh7rk` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`);

--
-- Constraints for table `property_vs_image`
--
ALTER TABLE `property_vs_image`
  ADD CONSTRAINT `FKdrv8bc11h2o2m8uumykk4cu57` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`);

--
-- Constraints for table `property_vs_nearby`
--
ALTER TABLE `property_vs_nearby`
  ADD CONSTRAINT `FKru2h6pp77q418s5973c7125de` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`);

--
-- Constraints for table `property_vs_pricedrop`
--
ALTER TABLE `property_vs_pricedrop`
  ADD CONSTRAINT `FKgwycvdturw8svnhj0pveb83bg` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`),
  ADD CONSTRAINT `FKnna1ewhlvt6hf3dmqx3rwd91k` FOREIGN KEY (`price_drop_id`) REFERENCES `master_price_drop` (`price_drop_id`);

--
-- Constraints for table `property_vs_space`
--
ALTER TABLE `property_vs_space`
  ADD CONSTRAINT `FKhd1vunghcip4rtk0511i53tf7` FOREIGN KEY (`sprule_id`) REFERENCES `master_space_rule` (`sprule_id`),
  ADD CONSTRAINT `FKklkdybjqkwqcsydd52tcjt4g0` FOREIGN KEY (`property_id`) REFERENCES `master_property` (`property_id`);

--
-- Constraints for table `room_vs_amenities`
--
ALTER TABLE `room_vs_amenities`
  ADD CONSTRAINT `FKblve2wd06ghlbt72q1onnu1ib` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`),
  ADD CONSTRAINT `FKm50c7up4jp1m0vl36325pktvw` FOREIGN KEY (`aminities_id`) REFERENCES `master_amenities` (`aminities_id`);

--
-- Constraints for table `room_vs_bed`
--
ALTER TABLE `room_vs_bed`
  ADD CONSTRAINT `FKhtr9fn5ifxsl6t6v6nvx3p7ee` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`);

--
-- Constraints for table `room_vs_cancellation`
--
ALTER TABLE `room_vs_cancellation`
  ADD CONSTRAINT `FKemhbtji1nvg41xa261axnd7vr` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`),
  ADD CONSTRAINT `FKr3pyo2gqwl71s8k5sm32rbjha` FOREIGN KEY (`cancellation_slab_id`) REFERENCES `master_cancellation_slab` (`cancellation_slab_id`);

--
-- Constraints for table `room_vs_host_discount`
--
ALTER TABLE `room_vs_host_discount`
  ADD CONSTRAINT `FK5sclwsu8pkvrqak6mcnxoxk6v` FOREIGN KEY (`dch_id`) REFERENCES `master_discount_category_host` (`dch_id`),
  ADD CONSTRAINT `FKq6d1mtvan7b7k0w3vm9m8wpti` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`);

--
-- Constraints for table `room_vs_image`
--
ALTER TABLE `room_vs_image`
  ADD CONSTRAINT `FKpamxay6j07bxeh97i0753dh6t` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`);

--
-- Constraints for table `room_vs_info`
--
ALTER TABLE `room_vs_info`
  ADD CONSTRAINT `FKdfb5k4oyqalu77f4016j5toea` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`);

--
-- Constraints for table `room_vs_meal`
--
ALTER TABLE `room_vs_meal`
  ADD CONSTRAINT `FK2qaygsqn2ca5x11fjhd0l5lxi` FOREIGN KEY (`meal_days_id`) REFERENCES `master_meal_days` (`meal_days_id`),
  ADD CONSTRAINT `FKbv9gqnudk5swt4bpm37f5p28b` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`),
  ADD CONSTRAINT `FKbwi7n73rr4g5ulx0i655j4jbw` FOREIGN KEY (`meal_type_id`) REFERENCES `master_meal_type` (`meal_type_id`),
  ADD CONSTRAINT `FKcpj8fovohx31bspss3xpi95sb` FOREIGN KEY (`mmpc_id`) REFERENCES `master_meal_price_category` (`mmpc_id`),
  ADD CONSTRAINT `FKgtj41ducucdx7bqgr4f32ky05` FOREIGN KEY (`meal_category_id`) REFERENCES `master_meal_category` (`meal_category_id`),
  ADD CONSTRAINT `FKq4sl9jriseumcbmun7gvuvgkb` FOREIGN KEY (`mpcmp_id`) REFERENCES `meal_plan_cat_vs_meal_plan` (`mpcmp_id`);

--
-- Constraints for table `room_vs_ora_discount`
--
ALTER TABLE `room_vs_ora_discount`
  ADD CONSTRAINT `FKk39qeirk6ipubv3iu87ehx7m4` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`),
  ADD CONSTRAINT `FKpct47x10hg0lpqb40of2uqjye` FOREIGN KEY (`dco_id`) REFERENCES `master_discount_category_ora` (`dco_id`);

--
-- Constraints for table `room_vs_ora_price`
--
ALTER TABLE `room_vs_ora_price`
  ADD CONSTRAINT `FK2mntqtwkqsqacajio6oi1y2kv` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`);

--
-- Constraints for table `room_vs_price`
--
ALTER TABLE `room_vs_price`
  ADD CONSTRAINT `FKea9eolxd563uccrp3pcq8shl7` FOREIGN KEY (`price_type_id`) REFERENCES `master_price_type` (`price_type_id`),
  ADD CONSTRAINT `FKiy1i3cks8ywbk5saucjrltpcc` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`);

--
-- Constraints for table `room_vs_specialties`
--
ALTER TABLE `room_vs_specialties`
  ADD CONSTRAINT `FKmjiqftc5rdrthmteghtchnkav` FOREIGN KEY (`room_id`) REFERENCES `master_room` (`room_id`),
  ADD CONSTRAINT `FKoe11vqm2reg89jso2t6d4mm7d` FOREIGN KEY (`specialties_id`) REFERENCES `master_specialties` (`specialties_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
