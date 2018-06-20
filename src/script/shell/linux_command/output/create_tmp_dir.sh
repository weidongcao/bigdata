#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.7.3 创建临时目录
# mktemp命令的-d选项告诉mktemp命令来创建一个临时目录而不是得时文件。
# 这样你就能用该目录进行任何需要的操作了,比如说创建其他的临时文件。

tempdir=$(mktemp -d dir.XXXXXX)
cd $tempdir

tempfile1=$(mktemp temp.XXXXXX)
tempfile2=$(mktemp temp.XXXXXX)

exec 7> $tempfile1
exec 8> $tempfile2

echo "Sending data to Directory $tempdir"
echo "This is a test line of adta for $tempfile1" >&7
echo "this is a test line of data for $tempfile2" >&8
