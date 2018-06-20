#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.5列出打开的文件描述符

exec 3> test5file1
exec 6> test5file2
exec 7< testfile

/usr/sbin/lsof -a -p $$ -d0,1,2,3,4,5,6,7
