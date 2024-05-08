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
-- Table structure for table `mt_tenant_secret`
--
DROP TABLE IF EXISTS `mt_tenant_secret`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_tenant_secret` (
  `secret_id` bigint NOT NULL COMMENT '密钥编号',
  `tenant_id` bigint NOT NULL COMMENT '租户编号',
  `sub_acc_id` bigint NOT NULL COMMENT '子账号编号',
  `app_key` char(7) NOT NULL COMMENT '应用key',
  `app_secret` char(32) NOT NULL COMMENT '应用密钥',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`secret_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户密钥';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_app`
--
DROP TABLE IF EXISTS `mt_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_app` (
  `app_id` bigint NOT NULL COMMENT '应用编号',
  `app_no` char(7) NOT NULL COMMENT '应用号码',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `home_page` varchar(128) NOT NULL COMMENT '官网地址',
  `details` TEXT DEFAULT NULL COMMENT '应用详情',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_app_instance`
--
DROP TABLE IF EXISTS `mt_app_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_app_instance` (
  `instance_id` bigint NOT NULL COMMENT '实例编号',
  `app_id` bigint NOT NULL COMMENT '应用编号',
  `tenant_id` bigint NOT NULL COMMENT '租户编号',
  `running_mode` tinyint NOT NULL COMMENT '运行模式',
  `deadline` datetime NOT NULL COMMENT '截至日期',
  `state` varchar(20) NOT NULL COMMENT '实例状态',
  `alias` varchar(30) DEFAULT NULL COMMENT '别名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`instance_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用实例';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_secret_auth`
--
DROP TABLE IF EXISTS `mt_secret_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_secret_auth` (
  `sec_auth_id` bigint NOT NULL COMMENT '密钥授权编号',
  `secret_id` bigint NOT NULL COMMENT '密钥编号',
  `instance_id` bigint NOT NULL COMMENT '实例编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`sec_auth_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='密钥授权';
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;