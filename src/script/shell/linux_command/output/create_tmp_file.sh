#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章呈现数据
# 15.7.1 创建本地临时文件 
# Page 325
# mktemp命令会用6个字符码替换这6个X，从而保证文件名在目录中是唯一的。
# 你可以创建多个临时文件，它可以保证每个文件都是唯一的。
# mktemp命令的输出正是它所创建的文件的名字。在脚本中使用mktemp命令时，
# 可能要将文件名保存到变量中，这样就能在后面的脚本中引用了，

tempfile=$(mktemp test19.XXXXXX)

exec 3>$tempfile
echo "This script writes to Temp file $tempfile"

echo "This is the first line" >&3
echo "This is the second line." >&3
echo "This is the last line." >&3
exec 3>&-

echo  "Done creating temp file. The contents are:"

cat $tempfile
rm -f $tempfile 2> /dev/null

# 这个脚本用mktemp命令来创建临时文件燕将文件名赋给$tempfile变量。
# 接着将这个临时文件作为文件描述符3的输出重定向文件。在将临时文件名显示在STDOUT之后，
# 向临时文件中写入几行文本，然后关闭了文件描述符，最后显示出临时文件的内容，并用rm命令将其删除。

