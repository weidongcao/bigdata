#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.7.2 在/tmp目录创建临时文件
# 由于mktemp命令返回了全路径名，你可以在Linux系统上的任何目录正点引用该临时文件，不管临时目录在哪里。

tempfile=$(mktemp -t temp.XXXXXX)

echo "This is a test file." > $tempfile
echo "This ai the second line of the test." >> $tempfile

echo "The temp file is located at: $tempfile"
cat $tempfile
rm -f $tempfile
