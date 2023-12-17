# *Hadoop学习*

## *大数据导论*

### 企业数据分析方向

数据如何产生：对客观事物的计量和记录产生数据

数据分析三个方向：现状分析、原因分析、预测分析（离线分析、实时分析、机器学习）

离线分析：面向过去，面向历史，分析已有的数据

实时分析：面向当下，分析实时产生的数据

机器学习：基于历史数据和当下产生的实时数据预测未来发生的事情

### *数据分析基本流程步骤*

1.明确分析目的和思路

2.数据收集

3.数据预处理

4.数据分析

5.数据展现

### *大数据时代*

###### 大数据5V特征

- 数据体量大（Volume）
- 种类、来源多样化(Variety)
- 低价值密度(Value)
- 速度快(Velocity)
- 数据的质量(Veracity)

### 分布式与集群

分布式：多台机器每台机器上部署**不同**的组件（多台分工，连接配合）

集群：多台机器每台机器上部署**相同**的组件（为全球提供服务）

存储：多台机器分布式存储

计算：多台机器分布式计算

## *Linux操作系统*

操作系统（operating system,简称OS）是管理计算机硬件与软件资源的程序

四大类：

1.桌面操作系统（Mac OS、Windows、Linux）

2.嵌入式操作系统(完全嵌入受控器件内部，在工业、军事、航空使用较多)（嵌入式Linux、WinCE、RTOS）

3.服务器操作系统：Unix、Linux、Windows Server、Netware

4.移动设备操作系统：Android、iOS、Harmony

##### Linux起源与发展

芬兰学生Linus Torvalds，开源免费，类Unix（外观和交互上类似）

Linux操作系统：Linux Kernel + GNU软件及系统软件 + 必要的应用程序

桌面版：Ubuntu 企业服务器版：Redhat（商业版）、Centos（社区版）



# Hadoop

分布式数据存储、分布式数据计算、分布式资源调度为一体

Hadoop是一个整体，其内部还细分为三个功能组件：

**HDFS** 可以构建分布式文件系统用于数据存储

**MapReduce** 提供编程接口供开发分布式计算程序

**YARN** 资源调度组件

Hadoop创始人 Doug Cutting

Google三篇论文：

《The Google file System》谷歌分布式文件系统GFS

《MapReduce: Simplified Data processing on Large Clusters》谷歌分布式计算框架MapReduce

《Bigtable: A Distribute Storage System for Structured Data》谷歌结构化数据存储系统

## Hadoop HDFS

Hadoop Distributed File System

去中心化模式：没有明确的中心，众多服务器之间基于特点规则进行同步协调

中心化模式：以一台服务器为中心，基于中心节点分配工作

HDFS集群：主角色：NameNode，从角色：DataNode，主角色辅助角色：SecondaryNameNode

NameNode：

- HDFS系统的主角色，是一个独立的进程
- 负责管理HDFS整个文件系统
- 负责管理DataNode

DataNode：

- HDFS从角色，是一个独立的进程
- 主要负责数据的存储，即存入数据和取出数据

SecondaryNameNode：

- NameNode的辅助，是一个独立的进程
- 主要帮助NameNode完成元数据整理工作

> start-dfs.sh一键启动HDFS集群
>
> 启动secondaryNameNode
>
> 读取core-site.xml内容（fs.defaultFS），确认NameNode所在机器，启动NameNode
>
> 读取workers内容，确认DataNode所在机器，启动全部DataNode
>
> stop-dfs.sh一键关闭HDFS集群

> 控制单进程的启停
>
> hadoop-daemon.sh (start|status|stop) (namenode|secondarynamenode|datanode)
>
> hdfs --demon (start|status|stop) (namenode|secondarynamenode|datanode)

