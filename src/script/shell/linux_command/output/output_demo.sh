#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.9 实例
# 文件重定向觉于脚本需要诗篇文件和输出文件时，这个样例脚本两件事都做了。它读取.csv格式的数据文件,
# 输出SQLINSERT 语句来将数据插入数据库

outfile='members.sql'
IFS=','
while read lname fname address city state zip
do
	cat >> $outfile << EOF
	insert into members(lname, fname, address, city, state, zip) values('$lname', '$fname', '$address', '$city', '$state', '$zip');
EOF
done < ${1}
