CREATE DATABASE  IF NOT EXISTS `myblog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `myblog`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 192.168.1.87    Database: myblog
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_attach`
--

DROP TABLE IF EXISTS `t_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fname` varchar(100) NOT NULL COMMENT '姓名',
  `ftype` varchar(50) DEFAULT NULL COMMENT '类型',
  `fkey` varchar(100) NOT NULL COMMENT '关键字',
  `author_id` int(11) NOT NULL COMMENT '作者ID',
  `created` int(11) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attach`
--

LOCK TABLES `t_attach` WRITE;
/*!40000 ALTER TABLE `t_attach` DISABLE KEYS */;
INSERT INTO `t_attach` VALUES (1,'40f5888b67c748df7efba008e7c2f9d2.jpg','image','/upload/2018/05/5e2a3e729da641edb382ca6d38271961.jpg',1,1525426873),(2,'2.png','image','/upload/2018/05/053d675cf80646d9925967bbdd0572b2.png',1,1525427059),(3,'3.jpg','image','/upload/2018/05/3c1376fab0a547cbbd565b6da915d482.jpg',1,1525427166),(4,'4.jpg','image','/upload/2018/05/234bdc2730ec40eba3f8532d58e2df7a.jpg',1,1525427801),(5,'5.jpg','image','/upload/2018/05/2d460aa256724b978d239cf5d9dc606e.jpg',1,1525427805),(6,'6.jpg','image','/upload/2018/05/fd7e17eeacc44819876877aa2163b938.jpg',1,1525427892),(7,'7.jpg','image','/upload/2018/05/96645133045c4660ae62c917f8c1a88d.jpg',1,1525427971),(8,'8.jpg','image','/upload/2018/05/a4ef0f91fefd4d0d990cc76ebfb9d4aa.jpg',1,1525427977),(9,'9.jpg','image','/upload/2018/05/78586577b5ad419eb1764d59997915f4.jpg',1,1525428055),(10,'10.jpg','image','/upload/2018/05/f92d9ed701734b4c8eb67e328649db8e.jpg',1,1525428115),(11,'11.jpg','image','/upload/2018/05/9fa2c1bba9374785a1780653fa8f10a8.jpg',1,1525428158),(12,'12.jpg','image','/upload/2018/05/5ad6225df3a24f1b8e15e024959bbb85.jpg',1,1525428255);
/*!40000 ALTER TABLE `t_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comments`
--

DROP TABLE IF EXISTS `t_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comments` (
  `coid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` int(11) NOT NULL DEFAULT '0' COMMENT '回复主键',
  `created` int(11) NOT NULL COMMENT '创建日期',
  `author` varchar(200) NOT NULL COMMENT '作者',
  `author_id` int(11) DEFAULT '0' COMMENT '作者ID',
  `owner_id` int(11) DEFAULT '0' COMMENT '拥有着ID',
  `mail` varchar(200) NOT NULL COMMENT '邮箱',
  `url` varchar(200) DEFAULT NULL COMMENT '链接',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `agent` varchar(200) DEFAULT NULL COMMENT '代理',
  `content` text NOT NULL COMMENT '内容',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `status` varchar(16) DEFAULT NULL COMMENT '状态',
  `parent` int(11) DEFAULT '0' COMMENT '上级',
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comments`
--

LOCK TABLES `t_comments` WRITE;
/*!40000 ALTER TABLE `t_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_contents`
--

DROP TABLE IF EXISTS `t_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_contents` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `slug` varchar(255) DEFAULT NULL COMMENT '内容缩略名',
  `thumb_img` varchar(255) DEFAULT NULL COMMENT '文章缩略图',
  `created` int(11) NOT NULL COMMENT '创建日期',
  `modified` int(11) DEFAULT NULL COMMENT '修改日期',
  `content` text COMMENT '内容',
  `author_id` int(11) NOT NULL COMMENT '作者id',
  `type` varchar(16) NOT NULL COMMENT '类型',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `fmt_type` varchar(16) DEFAULT 'markdown' COMMENT '解析类型',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签',
  `categories` varchar(200) DEFAULT NULL COMMENT '分类列表',
  `hits` int(11) DEFAULT '0' COMMENT '点击率',
  `comments_num` int(11) DEFAULT '0' COMMENT '评论数',
  `allow_comment` tinyint(1) DEFAULT '1' COMMENT '是否允许评论',
  `allow_ping` tinyint(1) DEFAULT NULL COMMENT '是否允许ping',
  `allow_feed` tinyint(1) DEFAULT NULL COMMENT '是否允许出现在聚合中',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='文章内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_contents`
--

LOCK TABLES `t_contents` WRITE;
/*!40000 ALTER TABLE `t_contents` DISABLE KEYS */;
INSERT INTO `t_contents` VALUES (1,'[收藏]博客',NULL,'',1525350088,1525423980,'[Intellij Idea](http://ice1000.org/categories/#IntelliJ)\r\n\r\n[Docker](http://www.cnblogs.com/studyzy/default.html?page=2)\r\n\r\n',1,'post','publish','markdown','blog','博客',1,0,1,1,1),(2,'[开源]apache顶级项目',NULL,'',1525351108,1525424011,'[ofbiz](http://ofbiz.apache.org/):开源电商项目\r\n========\r\n[maven](https://maven.apache.org/):jar包管理项目\r\n========\r\n',1,'post','publish','markdown','apache','apache',0,0,1,1,1),(3,'账号密码',NULL,'',1525351479,1525351648,'[联合并发网](http://www.pudn.com/)\r\n======\r\n- 邮箱: 2786885366@qq.com\r\n- 手机: 18229475136\r\n- 真实姓名: 陈成\r\n- 昵称: 逍遥陌陌\r\n- 密码: Aa2786885366+',1,'post','publish','markdown','账号密码','账号密码',2,0,1,1,1),(4,'[下载]项目下载网站',NULL,'',1525351884,1525423997,'# [联合开发网](http://www.pudn.com/) 评分:五颗星\r\n\r\n',1,'post','publish','markdown','download','项目下载',3,0,1,1,1),(5,'[一般]java开发POS机相关内容',NULL,'',1525352785,1525353746,'[http://www.javapos.com/](http://www.javapos.com/)\r\n\r\n[http://ofbiz.apache.org/](http://ofbiz.apache.org/)\r\n\r\n[java实现热敏打印机打印小票](https://blog.csdn.net/u014207158/article/details/53142256)\r\n\r\n[Java使用POS打印机（无驱）](http://www.cnblogs.com/ytlds/p/5656720.html)\r\n\r\n[使用Java实现简单串口通信](http://www.cnblogs.com/Dreamer-1/p/5523046.html)\r\n\r\n[顾客显示屏操作代码](http://bbs.chinaunix.net/thread-1669907-1-1.html)\r\n\r\n[java 打印机打印和开钱箱](https://www.thinksaas.cn/group/topic/177007/)\r\n\r\n[JAVA获取POS机（通过COM口）输入](http://blog.sina.com.cn/s/blog_49b531af0100jeiw.html)\r\n\r\n[Android连接网络打印机进行打印](http://www.cnblogs.com/LIANQQ/p/4587637.html)\r\n\r\n[Java直接控制打印机打印](https://blog.csdn.net/sqzhao/article/details/42519563)\r\n\r\n[Java实现POS打印机自定义无驱打印](http://www.cnblogs.com/fxwl/p/6597918.html)\r\n\r\n[JFinal小插件：Java驱动打印小票（80热敏打印机）](http://www.jfinal.com/project/34)\r\n\r\n',1,'post','publish','markdown','pos','项目经验',2,0,1,1,1),(6,'[收藏]导航网站',NULL,'',1525353885,1525423967,'[极客导航](http://www.jikedaohang.com/)\r\n\r\n[龙轩导航,高清电影,ppt模板,精品软件,网址导航,网盘搜索,镜像谷歌,免费资源](http://ilxdh.com/)\r\n\r\n[龙轩导航](http://www.iloveyoulong.com/)\r\n\r\n[linux导航](http://linux.ubuntu.org.cn/)',1,'post','publish','markdown','navigation','导航网站',0,0,1,1,1),(7,'[组件]javafx组件库:jfoenix',NULL,'',1525355959,1525424116,'[官网](http://www.jfoenix.com/)\r\n\r\n[Github](https://github.com/jfoenixadmin/JFoenix)',1,'post','publish','markdown','javafx','javafx',1,0,1,1,1),(8,'[收藏]在线书籍',NULL,'',1525357303,1525423948,'[ELKstack 中文指南](https://legacy.gitbook.com/book/chenryn/elk-stack-guide-cn/details)\r\n\r\n[Elasticsearch: 权威指南](https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html)',1,'post','publish','markdown','readbook','书籍',1,0,1,1,1),(9,'[收藏]技术论坛',NULL,'',1525358187,1525425748,'[Elastic中文社区](https://elasticsearch.cn/)\r\n\r\n[开发者头条 - 技术极客的头条新闻](https://toutiao.io/)',1,'post','publish','markdown','bbs','技术论坛',0,0,1,1,1),(10,'杂集',NULL,'',1525396757,1525431768,'[嘿嘿](https://www.baidu.com/s?wd=%E5%BC%BA%E5%8C%96%E5%9E%8B%E9%98%B4%E8%8C%8E%E5%A2%9E%E9%95%BF%E6%9C%AF%2B%E8%84%B1%E7%BB%86%E8%83%9E%E7%A7%BB%E6%A4%8D%E5%A2%9E%E7%B2%97&ie=UTF-8)\r\n\r\n[神价网 - 价格监控 一起惠返利网_178hui.com](http://www.178hui.com/shenjia/index.html)\r\n[西贴 - 历史价格查询|网上商城价格走势图](http://www.xitie.com/)\r\n[慢慢买 - 查询商品历史价格走势](http://tool.manmanbuy.com/HistoryLowest.aspx)\r\n\r\n[赚客吧 - 有奖一起赚](http://www.zuanke8.com/)\r\n\r\n[零壹财经 - 在这里，读懂新金融](http://www.01caijing.com/)\r\n\r\n[信用卡论坛 - 我爱卡会员社区-中国更大更权威的信用卡论坛](https://bbs.51credit.com/forum.php?mod=viewthread&tid=2989503)\r\n\r\n[什么值得买 - 路由选购攻略](https://post.smzdm.com/p/430693/)\r\n\r\n[知乎 - 买房时（预售期房的类型）有哪些方面值得注意？](https://www.zhihu.com/question/34367096)\r\n\r\n[微博 - 信用卡被盗了怎么办?](https://m.weibo.cn/status/4130959236343465#&gid=1&pid=1)\r\n\r\n[优酷 - 愚蠢——无可救药《云联惠的骗局》](http://v.youku.com/v_show/id_XMzQ2NjcyMTEyMA==.html)\r\n\r\n',1,'post','publish','markdown','娱乐,金融,购物','默认分类',1,0,1,1,1),(11,'[收藏]政府网站',NULL,'',1525396856,1525431309,'[天眼查-人人都在用商业安全工具](https://www.tianyancha.com/)\r\n\r\n[美江国际 - 0731房产网 - 新房网](http://floor.0731fdc.com/xinfo_2017.php?action=info&id=27897)\r\n\r\n[湘潭美江房地产开发有限公司联系方式－启信宝](http://www.qixin.com/company/d3d021dd-ce43-4670-9696-de5522aef412?token=46a586febf24a35ca449746907138a0b&from=bkdt#/risk/changerecords)\r\n\r\n[湖南省公安厅交通警察总队互联网交通安全服务管理平台](https://hn.122.gov.cn/)\r\n\r\n[国家市场监督管理总局](http://samr.saic.gov.cn/)\r\n\r\n[中国医院等级查询系统](https://www.hqms.org.cn/usp/roster/index.jsp)\r\n\r\n[中国机构检索](http://www.guide.conac.cn/lucene/gotoNetnameIndex#)\r\n\r\n[国家食品药品监督管理总局--数据查询](http://app1.sfda.gov.cn/datasearch/face3/dir.html)\r\n\r\n[2015年统计用区划代码和城乡划分代码](http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015/index.html)\r\n\r\n[中华人民共和国国家统计局>>统计标准](http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/)\r\n\r\n[最新县及县以上行政区划代码（截止2013年8月31日）](https://blog.csdn.net/beifeng600/article/details/39064981)\r\n\r\n[欢迎访问工业和信息化部ICP/IP地址/域名信息备案管理系统](http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action)\r\n\r\n[国家企业信用信息公示系统](http://www.gsxt.gov.cn/index.html)\r\n\r\n[全国公安机关互联网站安全服务平台](http://www.beian.gov.cn/portal/index)\r\n\r\n[中国互联网金融协会](https://dp.nifa.org.cn/)\r\n\r\n[中国人民银行征信中心](http://www.pbccrc.org.cn/)\r\n\r\n[中华人民共和国卫生部](http://www.moh.gov.cn/wsb/index_2013.shtml)\r\n\r\n[全国建筑市场监管公共服务平台](http://jzsc.mohurd.gov.cn/dataservice/query/comp/list)\r\n\r\n[基建通](http://www.cninct.com/)\r\n\r\n[中华人民共和国住房和城乡建设部 - 资质分级标准](http://www.mohurd.gov.cn/wbdt/xzzx/zzfjbz/)\r\n\r\n[国家职业资格证书查询](http://zscx.osta.org.cn/)\r\n\r\n[中国裁判文书网](http://wenshu.court.gov.cn/Index)\r\n\r\n[中华人民共和国国家卫生健康委员会](http://www.moh.gov.cn/)\r\n\r\n[中国物品编码中心](http://www.ancc.org.cn/)',1,'post','publish','markdown','government','政府网站',1,0,1,1,1),(12,'[收藏]在线学习视频网站',NULL,'',1525424611,1525425304,'[计蒜客 - 学习面向未来的计算机科学](https://www.jisuanke.com/)\r\n\r\n[慕课网-程序员的梦工厂](https://www.imooc.com/)\r\n\r\n[网易公开课](https://open.163.com/)\r\n\r\n[扣丁学堂](http://www.codingke.com/)\r\n\r\n[IT技能频道页-百度传课](https://chuanke.baidu.com/orp/nzt/channel/it#a3)\r\n\r\n[传智播客官网-一样的教育,不一样的品质](http://www.itcast.cn/)\r\n\r\n[极客学院IT在线教育平台-中国专业的IT职业在线教育平台](http://www.jikexueyuan.com/)',1,'post','publish','markdown','在线视频','在线视频,学习',0,0,1,1,1),(13,'[收藏]知名博客',NULL,'',1525424977,1525430630,'[首页 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/)\r\n\r\n[过往记忆 - 技术大拿](https://www.iteblog.com/)\r\n\r\n[博客频道 —SO JSON在线](https://www.sojson.com/blog/)\r\n\r\n[菜鸟程序员成长之路！](http://www.cuiyongzhi.com/)\r\n\r\n[V型知识库-互联网开发技术分享社区](http://www.vxzsk.com/)\r\n\r\n[码农网-程序员编程资料和编程经验分享平台](http://www.codeceo.com/)\r\n\r\n[并发编程网 – ifeve.com | 让天下没有难学的技术](http://ifeve.com/)\r\n\r\n[ImportNew - 专注Java & Android 技术分享](http://www.importnew.com/)\r\n\r\n',1,'post','publish','markdown','blog','博客',0,0,1,1,1),(14,'[收藏]在线文档',NULL,'',1525425219,1525429334,'[Mozilla基金会 - 更加专业的前端教程](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript)\r\n[菜鸟教程 - 学的不仅是技术，更是梦想！](http://www.runoob.com/)\r\n[w3school 在线教程](http://www.w3school.com.cn/)\r\n\r\n[手工部署Java Web项目 ECS-阿里云](https://help.aliyun.com/document_detail/51376.html)\r\n\r\n[ Spring官方教程翻译 ](http://doc.spring4all.com/spring-guildes/)\r\n\r\n[maven仓库](https://mvnrepository.com/)\r\n\r\n[jQuery API 中文文档 | jQuery API 中文在线手册 | jquery api 下载 | jquery api chm](http://jquery.cuishifeng.cn/)\r\n\r\n[英文文档- jQuery EasyUI](http://www.jeasyui.com/documentation/index.php#)\r\n\r\n[layer官方演示与讲解（jQuery弹出层插件）](http://layer.layui.com/)\r\n\r\n[JavaEE 7 API](https://docs.oracle.com/javaee/7/api/index.html)\r\n\r\n',1,'post','publish','markdown','综合文档,maven仓库,jQuery,EasyUI,layer,spring,aliyun','在线文档',0,0,1,1,1),(15,'[基础]Java ClassLoader 原理详细分析',NULL,'',1525426192,1525428264,'一、什么是ClassLoader?\r\n----\r\n\r\n大家都知道，当我们写好一个Java程序之后，不是管是CS还是BS应用，都是由若干个.class文件组织而成的一个完整的Java应用程序，当程序在运行时，即会调用该程序的一个入口函数来调用系统的相关功能，而这些功能都被封装在不同的class文件当中，所以经常要从这个class文件中要调用另外一个class文件中的方法，如果另外一个文件不存在的，则会引发系统异常。而程序在启动的时候，并不会一次性加载程序所要用的所有class文件，而是根据程序的需要，通过Java的类加载机制（ClassLoader）来动态加载某个class文件到内存当中的，从而只有class文件被载入到了内存之后，才能被其它class所引用。所以ClassLoader就是用来动态加载class文件到内存当中用的。\r\n\r\n二、Java默认提供的三个ClassLoader\r\n----\r\n\r\n- 1.Bootstrap ClassLoader：称为启动类加载器，是Java类加载层次中最顶层的类加载器，负责加载JDK中的核心类库，如：rt.jar、resources.jar、charsets.jar等，可通过如下程序获得该类加载器从哪些地方加载了相关的jar或class文件：\r\n\r\n```java\r\nURL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();  \r\nfor (int i = 0; i < urls.length; i++) {  \r\n    System.out.println(urls[i].toExternalForm());  \r\n}\r\n```  \r\n\r\n以下内容是上述程序从本机JDK环境所获得的结果：\r\n\r\nfile:/C:/Program%20Files/Java/jdk1.6.0_22/jre/lib/resources.jar\r\n\r\nfile:/C:/Program%20Files/Java/jdk1.6.0_22/jre/lib/rt.jar\r\n\r\nfile:/C:/Program%20Files/Java/jdk1.6.0_22/jre/lib/sunrsasign.jar\r\n\r\nfile:/C:/Program%20Files/Java/jdk1.6.0_22/jre/lib/jsse.jar\r\n\r\nfile:/C:/Program%20Files/Java/jdk1.6.0_22/jre/lib/jce.jar\r\n\r\nfile:/C:/Program%20Files/Java/jdk1.6.0_22/jre/lib/charsets.jar\r\n\r\nfile:/C:/Program%20Files/Java/jdk1.6.0_22/jre/classes/\r\n\r\n其实上述结果也是通过查找sun.boot.class.path这个系统属性所得知的。\r\n\r\n```java\r\nSystem.out.println(System.getProperty(\"sun.boot.class.path\"));\r\n```  \r\n\r\n\r\n打印结果：C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\sunrsasign.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\jsse.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\charsets.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\classes\r\n\r\n- 2.Extension ClassLoader：称为扩展类加载器，负责加载Java的扩展类库，默认加载JAVA_HOME/jre/lib/ext/目下的所有jar。\r\n\r\n- 3.App ClassLoader：称为系统类加载器，负责加载应用程序classpath目录下的所有jar和class文件。\r\n\r\n注意： 除了Java默认提供的三个ClassLoader之外，用户还可以根据需要定义自已的ClassLoader，而这些自定义的ClassLoader都必须继承自java.lang.ClassLoader类，也包括Java提供的另外二个ClassLoader（Extension ClassLoader和App ClassLoader）在内，但是Bootstrap ClassLoader不继承自ClassLoader，因为它不是一个普通的Java类，底层由C++编写，已嵌入到了JVM内核当中，当JVM启动后，Bootstrap ClassLoader也随着启动，负责加载完核心类库后，并构造Extension ClassLoader和App ClassLoader类加载器。\r\n\r\n三、ClassLoader加载类的原理\r\n----\r\n\r\n**1、原理介绍**\r\n\r\nClassLoader使用的是双亲委托模型来搜索类的，每个ClassLoader实例都有一个父类加载器的引用（不是继承的关系，是一个包含的关系），虚拟机内置的类加载器（Bootstrap ClassLoader）本身没有父类加载器，但可以用作其它ClassLoader实例的的父类加载器。当一个ClassLoader实例需要加载某个类时，它会试图亲自搜索某个类之前，先把这个任务委托给它的父类加载器，这个过程是由上至下依次检查的，首先由最顶层的类加载器Bootstrap ClassLoader试图加载，如果没加载到，则把任务转交给Extension ClassLoader试图加载，如果也没加载到，则转交给App ClassLoader 进行加载，如果它也没有加载得到的话，则返回给委托的发起者，由它到指定的文件系统或网络等URL中加载该类。如果它们都没有加载到这个类时，则抛出ClassNotFoundException异常。否则将这个找到的类生成一个类的定义，并将它加载到内存当中，最后返回这个类在内存中的Class实例对象。\r\n\r\n**2、为什么要使用双亲委托这种模型呢？**\r\n\r\n因为这样可以避免重复加载，当父亲已经加载了该类的时候，就没有必要子ClassLoader再加载一次。考虑到安全因素，我们试想一下，如果不使用这种委托模式，那我们就可以随时使用自定义的String来动态替代java核心api中定义的类型，这样会存在非常大的安全隐患，而双亲委托的方式，就可以避免这种情况，因为String已经在启动时就被引导类加载器（Bootstrcp ClassLoader）加载，所以用户自定义的ClassLoader永远也无法加载一个自己写的String，除非你改变JDK中ClassLoader搜索类的默认算法。\r\n\r\n**3、 但是JVM在搜索类的时候，又是如何判定两个class是相同的呢？**\r\n\r\n<u>JVM在判定两个class是否相同时，不仅要判断两个类名是否相同，而且要判断是否由同一个类加载器实例加载的。只有两者同时满足的情况下，JVM才认为这两个class是相同的。</u>就算两个class是同一份class字节码，如果被两个不同的ClassLoader实例所加载，JVM也会认为它们是两个不同class。比如网络上的一个Java类org.classloader.simple.NetClassLoaderSimple，javac编译之后生成字节码文件NetClassLoaderSimple.class，ClassLoaderA和ClassLoaderB这两个类加载器并读取了NetClassLoaderSimple.class文件，并分别定义出了java.lang.Class实例来表示这个类，对于JVM来说，它们是两个不同的实例对象，但它们确实是同一份字节码文件，如果试图将这个Class实例生成具体的对象进行转换时，就会抛运行时异常java.lang.ClassCaseException，提示这是两个不同的类型。现在通过实例来验证上述所描述的是否正确：\r\n\r\n1. 在web服务器上建一个org.classloader.simple.NetClassLoaderSimple.java类\r\n\r\n```java\r\npackage org.classloader.simple;  \r\n\r\npublic class NetClassLoaderSimple {  \r\n\r\n    private NetClassLoaderSimple instance;  \r\n\r\n    public void setNetClassLoaderSimple(Object obj) {  \r\n        this.instance = (NetClassLoaderSimple)obj;  \r\n    }  \r\n}\r\n```  \r\n\r\n\r\n2. 测试两个class是否相同（NetWorkClassLoader.java）\r\n\r\n```java\r\npackage classloader;  \r\n\r\npublic class NewworkClassLoaderTest {  \r\n\r\n    public static void main(String[] args) {  \r\n        try {  \r\n            //测试加载网络中的class文件  \r\n            String rootUrl = \"http://localhost:8080/httpweb/classes\";  \r\n            String className = \"org.classloader.simple.NetClassLoaderSimple\";  \r\n            NetworkClassLoader ncl1 = new NetworkClassLoader(rootUrl);  \r\n            NetworkClassLoader ncl2 = new NetworkClassLoader(rootUrl);  \r\n            Class<?> clazz1 = ncl1.loadClass(className);  \r\n            Class<?> clazz2 = ncl2.loadClass(className);  \r\n            Object obj1 = clazz1.newInstance();  \r\n            Object obj2 = clazz2.newInstance();  \r\n            clazz1.getMethod(\"setNetClassLoaderSimple\", Object.class).invoke(obj1, obj2);  \r\n        } catch (Exception e) {  \r\n            e.printStackTrace();  \r\n        }  \r\n    }  \r\n\r\n}\r\n```  \r\n\r\n首先获得网络上一个class文件的二进制名称，然后通过自定义的类加载器NetworkClassLoader创建两个实例，并根据网络地址分别加载这份class，并得到这两个ClassLoader实例加载后生成的Class实例clazz1和clazz2，最后将这两个Class实例分别生成具体的实例对象obj1和obj2，再通过反射调用clazz1中的setNetClassLoaderSimple方法。\r\n\r\n3. 查看测试结果\r\n\r\n![alt](/upload/2018/05/5e2a3e729da641edb382ca6d38271961.jpg)\r\n\r\n**结论：从结果中可以看出，虽然是同一份class字节码文件，但是由于被两个不同的ClassLoader实例所加载，所以JVM认为它们就是两个不同的类。**\r\n\r\n**4、ClassLoader的体系架构：**\r\n\r\n![alt](/upload/2018/05/053d675cf80646d9925967bbdd0572b2.png)\r\n\r\n**验证ClassLoader加载类的原理：**\r\n\r\n测试1：打印ClassLoader类的层次结构，请看下面这段代码：\r\n\r\n```java\r\n//获得加载ClassLoaderTest.class这个类的类加载器  \r\nClassLoader loader = ClassLoaderTest.class.getClassLoader();    \r\nwhile(loader != null) {  \r\n    System.out.println(loader);  \r\n     //获得父类加载器的引用  \r\n    loader = loader.getParent();   \r\n}  \r\nSystem.out.println(loader);\r\n```  \r\n\r\n\r\n打印结果：\r\n\r\n![alt](/upload/2018/05/3c1376fab0a547cbbd565b6da915d482.jpg)\r\n\r\n第一行结果说明：ClassLoaderTest的类加载器是AppClassLoader。\r\n\r\n第二行结果说明：AppClassLoader的类加器是ExtClassLoader，即parent=ExtClassLoader。\r\n\r\n第三行结果说明：ExtClassLoader的类加器是Bootstrap ClassLoader，因为Bootstrap ClassLoader不是一个普通的Java类，所以ExtClassLoader的parent=null，所以第三行的打印结果为null就是这个原因。\r\n\r\n测试2：将ClassLoaderTest.class打包成ClassLoaderTest.jar，放到Extension ClassLoader的加载目录下（JAVA_HOME/jre/lib/ext），然后重新运行这个程序，得到的结果会是什么样呢？\r\n\r\n![alt](/upload/2018/05/234bdc2730ec40eba3f8532d58e2df7a.jpg)\r\n\r\n打印结果：\r\n\r\n![alt](/upload/2018/05/2d460aa256724b978d239cf5d9dc606e.jpg)\r\n\r\n打印结果分析：\r\n\r\n为什么第一行的结果是ExtClassLoader呢？\r\n\r\n因为ClassLoader的委托模型机制，当我们要用ClassLoaderTest.class这个类的时候，AppClassLoader在试图加载之前，先委托给Bootstrcp ClassLoader，Bootstracp ClassLoader发现自己没找到，它就告诉ExtClassLoader，兄弟，我这里没有这个类，你去加载看看，然后Extension ClassLoader拿着这个类去它指定的类路径（JAVA_HOME/jre/lib/ext）试图加载，唉，它发现在ClassLoaderTest.jar这样一个文件中包含ClassLoaderTest.class这样的一个文件，然后它把找到的这个类加载到内存当中，并生成这个类的Class实例对象，最后把这个实例返回。所以ClassLoaderTest.class的类加载器是ExtClassLoader。\r\n\r\n第二行的结果为null，是因为ExtClassLoader的父类加载器是Bootstrap ClassLoader。\r\n\r\n测试3：用Bootstrcp ClassLoader来加载ClassLoaderTest.class，有两种方式：\r\n\r\n1、在jvm中添加-Xbootclasspath参数，指定Bootstrcp ClassLoader加载类的路径，并追加我们自已的jar（ClassTestLoader.jar）\r\n\r\n2、将class文件放到JAVA_HOME/jre/classes/目录下（上面有提到）\r\n\r\n方式1：（我用的是Eclipse开发工具，用命令行是在java命令后面添加-Xbootclasspath参数）\r\n\r\n打开Run配置对话框：\r\n\r\n![alt](/upload/2018/05/fd7e17eeacc44819876877aa2163b938.jpg)\r\n\r\n配置好如图中所述的参数后，重新运行程序，产的结果如下所示：（类加载的过程，只摘下了一部份）\r\n\r\n打印结果：\r\n\r\n```java\r\n[Loaded java.io.FileReader from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded sun.nio.cs.StreamDecoder from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.ArrayList from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.lang.reflect.Array from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.Locale from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.ConcurrentMap from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.ConcurrentHashMap from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.locks.Lock from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.locks.ReentrantLock from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.ConcurrentHashMap$Segment from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.locks.AbstractOwnableSynchronizer from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.locks.AbstractQueuedSynchronizer from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.locks.ReentrantLock$Sync from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.locks.ReentrantLock$NonfairSync from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.locks.AbstractQueuedSynchronizer$Node from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.concurrent.ConcurrentHashMap$HashEntry from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.lang.CharacterDataLatin1 from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.io.ObjectStreamClass from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded sun.net.www.ParseUtil from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.BitSet from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.net.Parts from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.net.URLStreamHandler from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded sun.net.www.protocol.file.Handler from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.util.HashSet from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded sun.net.www.protocol.jar.Handler from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded sun.misc.Launcher$AppClassLoader from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded sun.misc.Launcher$AppClassLoader$1 from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.lang.SystemClassLoaderAction from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Path C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\classes]  \r\n[Loaded classloader.ClassLoaderTest from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\classes]  \r\nnull  //这是打印的结果  \r\nC:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar;  \r\nC:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\sunrsasign.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\jsse.jar;  \r\nC:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\charsets.jar;  \r\nC:\\Program Files\\Java\\jdk1.6.0_22\\jre\\classes;c:\\ClassLoaderTest.jar    \r\n//这一段是System.out.println(System.getProperty(\"sun.boot.class.path\"));打印出来的。这个路径就是Bootstrcp ClassLoader默认搜索类的路径  \r\n[Loaded java.lang.Shutdown from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]  \r\n[Loaded java.lang.Shutdown$Lock from C:\\Program Files\\Java\\jdk1.6.0_22\\jre\\lib\\rt.jar]\r\n```  \r\n\r\n方式2：将ClassLoaderTest.jar解压后，放到JAVA_HOME/jre/classes目录下，如下图所示：\r\n\r\n提示：jre目录下默认没有classes目录，需要自己手动创建一个\r\n\r\n![alt](/upload/2018/05/96645133045c4660ae62c917f8c1a88d.jpg)\r\n\r\n打印结果：\r\n\r\n![alt](/upload/2018/05/a4ef0f91fefd4d0d990cc76ebfb9d4aa.jpg)\r\n\r\n从结果中可以看出，两种方式都实现了将ClassLoaderTest.class由Bootstrcp ClassLoader加载成功了。\r\n\r\n四、定义自已的ClassLoader\r\n----\r\n\r\n既然JVM已经提供了默认的类加载器，为什么还要定义自已的类加载器呢？\r\n\r\n因为Java中提供的默认ClassLoader，只加载指定目录下的jar和class，如果我们想加载其它位置的类或jar时，比如：我要加载网络上的一个class文件，通过动态加载到内存之后，要调用这个类中的方法实现我的业务逻辑。在这样的情况下，默认的ClassLoader就不能满足我们的需求了，所以需要定义自己的ClassLoader。\r\n\r\n定义自已的类加载器分为两步：\r\n\r\n1、继承java.lang.ClassLoader\r\n\r\n2、重写父类的findClass方法\r\n\r\n读者可能在这里有疑问，父类有那么多方法，为什么偏偏只重写findClass方法？\r\n\r\n因为JDK已经在loadClass方法中帮我们实现了ClassLoader搜索类的算法，当在loadClass方法中搜索不到类时，loadClass方法就会调用findClass方法来搜索类，所以我们只需重写该方法即可。如没有特殊的要求，一般不建议重写loadClass搜索类的算法。下图是API中ClassLoader的loadClass方法：\r\n\r\n![alt](/upload/2018/05/78586577b5ad419eb1764d59997915f4.jpg)\r\n\r\n示例：自定义一个NetworkClassLoader，用于加载网络上的class文件\r\n\r\n```java\r\npackage classloader;  \r\n\r\nimport java.io.ByteArrayOutputStream;  \r\nimport java.io.InputStream;  \r\nimport java.net.URL;  \r\n\r\n/** \r\n * 加载网络class的ClassLoader \r\n */  \r\npublic class NetworkClassLoader extends ClassLoader {  \r\n\r\n    private String rootUrl;  \r\n\r\n    public NetworkClassLoader(String rootUrl) {  \r\n        this.rootUrl = rootUrl;  \r\n    }  \r\n\r\n    @Override  \r\n    protected Class<?> findClass(String name) throws ClassNotFoundException {  \r\n        Class clazz = null;//this.findLoadedClass(name); // 父类已加载     \r\n        //if (clazz == null) {  //检查该类是否已被加载过  \r\n            byte[] classData = getClassData(name);  //根据类的二进制名称,获得该class文件的字节码数组  \r\n            if (classData == null) {  \r\n                throw new ClassNotFoundException();  \r\n            }  \r\n            clazz = defineClass(name, classData, 0, classData.length);  //将class的字节码数组转换成Class类的实例  \r\n        //}   \r\n        return clazz;  \r\n    }  \r\n\r\n    private byte[] getClassData(String name) {  \r\n        InputStream is = null;  \r\n        try {  \r\n            String path = classNameToPath(name);  \r\n            URL url = new URL(path);  \r\n            byte[] buff = new byte[1024*4];  \r\n            int len = -1;  \r\n            is = url.openStream();  \r\n            ByteArrayOutputStream baos = new ByteArrayOutputStream();  \r\n            while((len = is.read(buff)) != -1) {  \r\n                baos.write(buff,0,len);  \r\n            }  \r\n            return baos.toByteArray();  \r\n        } catch (Exception e) {  \r\n            e.printStackTrace();  \r\n        } finally {  \r\n            if (is != null) {  \r\n               try {  \r\n                  is.close();  \r\n               } catch(IOException e) {  \r\n                  e.printStackTrace();  \r\n               }  \r\n            }  \r\n        }  \r\n        return null;  \r\n    }  \r\n\r\n    private String classNameToPath(String name) {  \r\n        return rootUrl + \"/\" + name.replace(\".\", \"/\") + \".class\";  \r\n    }  \r\n\r\n}\r\n```  \r\n\r\n测试类：\r\n\r\n```java\r\npackage classloader;  \r\n\r\npublic class ClassLoaderTest {  \r\n\r\n    public static void main(String[] args) {  \r\n        try {  \r\n            /*ClassLoader loader = ClassLoaderTest.class.getClassLoader();  //获得ClassLoaderTest这个类的类加载器 \r\n            while(loader != null) { \r\n                System.out.println(loader); \r\n                loader = loader.getParent();    //获得父加载器的引用 \r\n            } \r\n            System.out.println(loader);*/  \r\n\r\n            String rootUrl = \"http://localhost:8080/httpweb/classes\";  \r\n            NetworkClassLoader networkClassLoader = new NetworkClassLoader(rootUrl);  \r\n            String classname = \"org.classloader.simple.NetClassLoaderTest\";  \r\n            Class clazz = networkClassLoader.loadClass(classname);  \r\n            System.out.println(clazz.getClassLoader());  \r\n\r\n        } catch (Exception e) {  \r\n            e.printStackTrace();  \r\n        }  \r\n    }  \r\n\r\n}\r\n```  \r\n\r\n打印结果：\r\n\r\n![alt](/upload/2018/05/f92d9ed701734b4c8eb67e328649db8e.jpg)\r\n\r\n下图是我机器上web服务器的目录结构：\r\n\r\n![alt](/upload/2018/05/9fa2c1bba9374785a1780653fa8f10a8.jpg)\r\n\r\n目前常用web服务器中都定义了自己的类加载器，用于加载web应用指定目录下的类库（jar或class），如：Weblogic、Jboss、tomcat等，下面我以Tomcat为例，展示该web容器都定义了哪些个类加载器：\r\n\r\n1、新建一个web工程httpweb\r\n\r\n2、新建一个ClassLoaderServletTest，用于打印web容器中的ClassLoader层次结构\r\n\r\n```java\r\nimport java.io.IOException;  \r\nimport java.io.PrintWriter;  \r\n\r\nimport javax.servlet.ServletException;  \r\nimport javax.servlet.http.HttpServlet;  \r\nimport javax.servlet.http.HttpServletRequest;  \r\nimport javax.servlet.http.HttpServletResponse;  \r\n\r\npublic class ClassLoaderServletTest extends HttpServlet {  \r\n\r\n    public void doGet(HttpServletRequest request, HttpServletResponse response)  \r\n            throws ServletException, IOException {  \r\n\r\n        response.setContentType(\"text/html\");  \r\n        PrintWriter out = response.getWriter();  \r\n        ClassLoader loader = this.getClass().getClassLoader();  \r\n        while(loader != null) {  \r\n            out.write(loader.getClass().getName()+\"<br/>\");  \r\n            loader = loader.getParent();  \r\n        }  \r\n        out.write(String.valueOf(loader));  \r\n        out.flush();  \r\n        out.close();  \r\n    }  \r\n\r\n    public void doPost(HttpServletRequest request, HttpServletResponse response)  \r\n            throws ServletException, IOException {  \r\n        this.doGet(request, response);  \r\n    }  \r\n\r\n}\r\n```  \r\n\r\n3、配置Servlet，并启动服务\r\n```xml\r\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \r\n<web-app version=\"2.4\"   \r\n    xmlns=\"http://java.sun.com/xml/ns/j2ee\"   \r\n    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"   \r\n    xsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee   \r\n\r\nhttp://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd\">\r\n\r\n  <servlet>  \r\n    <servlet-name>ClassLoaderServletTest</servlet-name>  \r\n    <servlet-class>ClassLoaderServletTest</servlet-class>  \r\n  </servlet>  \r\n\r\n  <servlet-mapping>  \r\n    <servlet-name>ClassLoaderServletTest</servlet-name>  \r\n    <url-pattern>/servlet/ClassLoaderServletTest</url-pattern>  \r\n  </servlet-mapping>  \r\n  <welcome-file-list>  \r\n    <welcome-file>index.jsp</welcome-file>  \r\n  </welcome-file-list>  \r\n</web-app>\r\n```  \r\n\r\n4、访问Servlet，获得显示结果\r\n![alt](/upload/2018/05/5ad6225df3a24f1b8e15e024959bbb85.jpg)',1,'post','publish','markdown','ClassLoader,JavaSE','java',3,0,1,1,1),(16,'[收藏]免费(收费)的学习视频下载网站',NULL,'',1525428426,1525428439,'\r\n[怪兽学院](http://www.guaishouxueyuan.net/)',1,'post','publish','markdown','菜鸟要飞','学习',0,0,1,1,1);
/*!40000 ALTER TABLE `t_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_logs`
--

DROP TABLE IF EXISTS `t_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `action` varchar(100) NOT NULL COMMENT '产生的动作',
  `data` varchar(2000) DEFAULT NULL COMMENT '产生的数据',
  `author_id` int(11) NOT NULL COMMENT '产生的id',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `created` int(10) NOT NULL COMMENT '记录日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_logs`
--

LOCK TABLES `t_logs` WRITE;
/*!40000 ALTER TABLE `t_logs` DISABLE KEYS */;
INSERT INTO `t_logs` VALUES (1,'登录后台','{\"username\":\"admin\",\"password\":\"123456\",\"remeberMe\":null}',1,'0:0:0:0:0:0:0:1',1525350015),(2,'登录后台','{\"username\":\"admin\",\"password\":\"123456\",\"remeberMe\":null}',1,'0:0:0:0:0:0:0:1',1525396690),(3,'登录后台','{\"username\":\"admin\",\"password\":\"123456\",\"remeberMe\":null}',1,'0:0:0:0:0:0:0:1',1525421719);
/*!40000 ALTER TABLE `t_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_metas`
--

DROP TABLE IF EXISTS `t_metas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_metas` (
  `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '分类名称',
  `slug` varchar(200) DEFAULT NULL COMMENT '分类缩写',
  `type` varchar(32) NOT NULL COMMENT '分类类型',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `sort` tinyint(4) DEFAULT '0' COMMENT '排序',
  `parent` int(11) DEFAULT '0' COMMENT '上级',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_metas`
--

LOCK TABLES `t_metas` WRITE;
/*!40000 ALTER TABLE `t_metas` DISABLE KEYS */;
INSERT INTO `t_metas` VALUES (1,'默认分类',NULL,'category',NULL,0,0),(2,'博客',NULL,'category',NULL,NULL,NULL),(3,'blog','blog','tag',NULL,NULL,NULL),(4,'apache',NULL,'category',NULL,NULL,NULL),(5,'apache','apache','tag',NULL,NULL,NULL),(6,'账号密码',NULL,'category',NULL,NULL,NULL),(7,'账号密码','账号密码','tag',NULL,NULL,NULL),(8,'项目下载',NULL,'category',NULL,NULL,NULL),(9,'download','download','tag',NULL,NULL,NULL),(10,'项目经验',NULL,'category',NULL,NULL,NULL),(11,'pos','pos','tag',NULL,NULL,NULL),(12,'导航网站',NULL,'category',NULL,NULL,NULL),(13,'navigation','navigation','tag',NULL,NULL,NULL),(14,'java',NULL,'category',NULL,NULL,NULL),(15,'spring',NULL,'category',NULL,NULL,NULL),(16,'javafx',NULL,'category',NULL,NULL,NULL),(17,'javafx','javafx','tag',NULL,NULL,NULL),(18,'书籍',NULL,'category',NULL,NULL,NULL),(19,'readbook','readbook','tag',NULL,NULL,NULL),(20,'技术论坛',NULL,'category',NULL,NULL,NULL),(21,'bbs','bbs','tag',NULL,NULL,NULL),(22,'政府网站',NULL,'category',NULL,NULL,NULL),(23,'government','government','tag',NULL,NULL,NULL),(24,'学习',NULL,'category',NULL,NULL,NULL),(25,'在线视频',NULL,'category',NULL,NULL,NULL),(26,'在线视频','在线视频','tag',NULL,NULL,NULL),(27,'在线文档',NULL,'category',NULL,NULL,NULL),(28,'综合文档','综合文档','tag',NULL,NULL,NULL),(29,'ClassLoader','ClassLoader','tag',NULL,NULL,NULL),(30,'JavaSE','JavaSE','tag',NULL,NULL,NULL),(31,'菜鸟要飞','菜鸟要飞','tag',NULL,NULL,NULL),(32,'maven仓库','maven仓库','tag',NULL,NULL,NULL),(33,'jQuery','jQuery','tag',NULL,NULL,NULL),(34,'EasyUI','EasyUI','tag',NULL,NULL,NULL),(35,'layer','layer','tag',NULL,NULL,NULL),(36,'spring','spring','tag',NULL,NULL,NULL),(37,'aliyun','aliyun','tag',NULL,NULL,NULL),(38,'娱乐','娱乐','tag',NULL,NULL,NULL),(39,'金融','金融','tag',NULL,NULL,NULL),(40,'购物','购物','tag',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_metas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_options`
--

DROP TABLE IF EXISTS `t_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_options` (
  `name` varchar(100) NOT NULL COMMENT '配置名称',
  `value` text COMMENT '配置值',
  `description` varchar(255) DEFAULT NULL COMMENT '配置描述',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置选项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_options`
--

LOCK TABLES `t_options` WRITE;
/*!40000 ALTER TABLE `t_options` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_relationships`
--

DROP TABLE IF EXISTS `t_relationships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_relationships` (
  `cid` int(11) NOT NULL COMMENT '内容主键',
  `mid` int(11) NOT NULL COMMENT '项目主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_relationships`
--

LOCK TABLES `t_relationships` WRITE;
/*!40000 ALTER TABLE `t_relationships` DISABLE KEYS */;
INSERT INTO `t_relationships` VALUES (1,3),(1,2),(2,5),(2,4),(3,7),(3,6),(4,9),(4,8),(5,11),(5,10),(6,13),(6,12),(7,17),(7,16),(8,19),(8,18),(9,21),(9,20),(10,1),(11,23),(11,22),(12,26),(12,25),(12,24),(13,3),(13,2),(14,28),(14,27),(15,29),(15,30),(15,14),(16,31),(16,24),(14,32),(14,33),(14,34),(14,35),(14,36),(14,37),(10,38),(10,39),(10,40);
/*!40000 ALTER TABLE `t_relationships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_users`
--

DROP TABLE IF EXISTS `t_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `home_url` varchar(255) DEFAULT NULL COMMENT '链接',
  `screen_name` varchar(100) DEFAULT NULL COMMENT '显示的名称',
  `created` int(11) NOT NULL COMMENT '注册日期',
  `activated` int(11) DEFAULT NULL COMMENT '最后活动日期',
  `logged` int(11) DEFAULT NULL COMMENT '上次登陆日期',
  `group_name` varchar(16) DEFAULT NULL COMMENT '用户组',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_users`
--

LOCK TABLES `t_users` WRITE;
/*!40000 ALTER TABLE `t_users` DISABLE KEYS */;
INSERT INTO `t_users` VALUES (1,'admin','123456',NULL,NULL,NULL,123456,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-05 11:41:34
