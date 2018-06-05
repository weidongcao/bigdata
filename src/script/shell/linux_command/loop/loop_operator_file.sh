#!/bin/bash
# 循环处理文件数据

IFS.OLD=$IFS
IFS=$'\n'
for entry in $(cat /etc/passwd);do
	echo "Values in $entry -"
	IFS=:
	for value in $entry; do
		echo "	$value"
	done
done
