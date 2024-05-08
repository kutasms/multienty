/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `mt_plugin`
--
DROP TABLE IF EXISTS `mt_plugin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_plugin` (
  `plugin_id` bigint(20) NOT NULL COMMENT '插件编号',
  `name` varchar(128) NOT NULL COMMENT '插件名称',
  `type` smallint NOT NULL COMMENT '插件类型',
  `type_name` varchar(64) NOT NULL COMMENT '插件类型名称',
  `alias` varchar(128) DEFAULT NULL COMMENT '别名',
  `details` MEDIUMTEXT DEFAULT NULL COMMENT '详情',
  `running_mode` tinyint(4) NOT NULL COMMENT '运行模式',
  `source` varchar(10) NOT NULL COMMENT '来源',
  `running_state` varchar(20) NOT NULL COMMENT '运行状态',
  `lib_name` varchar(128) NOT NULL COMMENT '库名称',
  `package_name` varchar(128) NOT NULL COMMENT '包名称',
  `class_name` varchar(256) NOT NULL COMMENT '类名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`plugin_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='插件';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_plugin_price_strategy`
--
DROP TABLE IF EXISTS `mt_plugin_price_strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_plugin_price_strategy` (
  `strategy_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '插件价格策略编号',
  `plugin_id` bigint(20) NOT NULL COMMENT '插件编号',
  `price` decimal(12,2) DEFAULT NULL COMMENT '价格',
  `scope` varchar(10) NOT NULL COMMENT '作用域',
  `is_free` bit NOT NULL COMMENT '是否免费',
  `triable` bit(1) NOT NULL COMMENT '可试用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`strategy_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='插件价格策略';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_plugin_contract`
--
DROP TABLE IF EXISTS `mt_plugin_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_plugin_contract` (
  `contract_id` bigint(20) NOT NULL COMMENT '插件合约编号',
  `plugin_id` bigint(20) NOT NULL COMMENT '插件编号',
  `deadline` datetime NOT NULL COMMENT '截至时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`contract_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='插件合约';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_plugin_stat`
--
DROP TABLE IF EXISTS `mt_plugin_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_plugin_stat` (
  `plugin_id` bigint(20) NOT NULL COMMENT '插件编号',
  `star_count` int NOT NULL COMMENT '点赞数',
  `watch_count` int NOT NULL COMMENT '关注人数',
  `apply_count` int NOT NULL COMMENT '应用次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`plugin_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='插件统计';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_plugin_dependency`
--
DROP TABLE IF EXISTS `mt_plugin_dependency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_plugin_dependency` (
  `dependency_id` bigint(20) NOT NULL COMMENT '插件依赖编号',
  `plugin_id` bigint(20) NOT NULL COMMENT '插件编号',
  `dep_res_name` int NOT NULL COMMENT '依赖资源名',
  `ext_name` int NOT NULL COMMENT '文件扩展名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dependency_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='插件依赖';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_plugin_res`
--
DROP TABLE IF EXISTS `mt_plugin_res`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_plugin_res` (
  `res_id` bigint(20) NOT NULL COMMENT '插件资源编号',
  `plugin_id` bigint(20) NOT NULL COMMENT '插件编号',
  `res_url` varchar(256) NOT NULL COMMENT '资源URL地址',
  `file_name` varchar(128) NOT NULL COMMENT '文件名',
  `file_id` bigint(20) NOT NULL COMMENT '文件编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`res_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='插件资源';
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;