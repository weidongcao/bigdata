#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第17章创建函数
# 17.6 创建库
# Page 371
# 使用函数库的关键在于source命令。
# source命令会在当前shell上下文中执行命令，而不是创建一个新shell。
# 可以用source命令来在shell脚本中运行库文件脚本。
# 这样脚本就可以使用库中的函数了。
# source命令有个快捷键的别名，称作点操作(dot operator).
# 要在Shell脚本中运行function_repo.sh库文件，只需要添加下面这行
# . ./function_repo.sh
# 这个例子假定function_repo.sh库文件和shell脚本位于同一目录。
# 如果不是，就需要使用相应路径访问该文件。

# . ./function_repo.sh

value1=10
value2=5
result1=$(addem $value1 $value2)
result2=$(multem $value1 $value2)
result3=$(divem $value1 $value2)
echo "The result of adding them is : $result1"
echo "The result of multiplying then is : $result2"
echo "The result of dividing them is : $result3"

