#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.4.1 创建输出文件描述符

# 可以用exec命令来给输出分配文件描述符。和标准的文件描述符一样，
# 一旦将另一个文件描述符分配给一个文件，这个重定向就会一直有效，直到你重新分配。
# 这里有个在脚本中使用其他文件描述符的简单例子。

exec 3>test13out
echo "This should display on the monitor"
echo "and this shouldbe stored in the file" >&3
echo "Then this should be back on the monitor"

# 这个脚本用exec命令将文件描述符3重定向到另一个文件。当脚本执行echo语句时，
# 输出内容会像预想中那样显示在STDOUT上，但你重定向到文件描述符3的那行echo语句的输入却
# 进入了另一个文件。这样你就可以在显示器上保持正常的输出，而将特定信息重定向到文件中
# 也可以不用创建新文件，而是使用exec命令来将输出追加到现有文件中。
# exec 3>>test13out
