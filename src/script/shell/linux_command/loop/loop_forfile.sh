#!/bin/bash
# 《Linux命令行与Shell编程脚本大全》第13章 Page 264
# 测试for循环读取文件内容

# States文件中每一行有一个州，而不是通过空格分隔的，for命令仍然以每次一行的方式遍历了cat命令的输出。
# 假定每个州都在单独的一行上。
# 如果你列出了一个名字中有空格的州，for命令仍然会将每个单词当作单独的值。
# 造成这种问题的原因是特殊的环境变量IFS，叫作内部字段分隔符(Internal Field Separator)
# IFS环境变量定义了Bash Shell用作字段符的一系列字符。默认情况下，Bash Shell会将下列字符当作字段分隔符:
# 	空格
#	制表符
# 	换行符
# 要解决这个问题可以在脚本中临时更改IFS环境变量的值来限制被Bash Shell当作字段分隔符的字符。

# 在处理代码是不是较大的脚本时，可能在一个地方需要修改IFS的值，然后忽略这次修改，在脚本的其他地方
# 继续沿用IFS的默认值。一个可参考的实践是在改变IFS之前保存原来的IFS值，之后再恢复它
IFS.OLD=$IFS

# 改变IFS值
IFS=$'\n'

file="states"
for state in $(cat $file)
do 
	echo "Visit beautiful $state"
done

# 恢复IFS默认值
IFS=$IFS.OLD
# stats 文件内容:
# Alabama
# Alaska
# Arizona
# Arkansas
# Colorado
# Connecticut
# Delaware
# Florida
# Georgia
# AAA aaa
