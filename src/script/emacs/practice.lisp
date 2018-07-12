;; This buffer is for text that is not saved, and for Lisp evaluation.
;; To create a file, visit it with C-x C-f and enter text in its buffer.
(defun hello (name) (insert (format "Hello %s!\n" name)))

(hello "you")

(let ((local-name "dong"))
  (switch-to-buffer-other-window "*test*")
  (erase-buffer)
  (hello local-name)
  (other-window 1))

(defun greeting (name)
  (let ((yname "WeiDong"))
    (insert (format "Hello %s!\n\nI am %s."
		    name      ; the argument of the function
		    yname     ; the let-bound variable "WeiDong"
		    ))))

;;之后执行:
(greeting "you")

;;有些函数可以和用户交互:
(read-from-minibuffer "Enter your name:")

(defun greeting (fname)
  (let ((yname (read-from-minibuffer "Enter yout name: ")))
    (insert (format "Hello! \nI am %s and you are %s."
		    fname  ;the argument of the function
		    yname  ;the let-bound var, entered at promt
		    ))))
(greeting "SSS")

;;我们让结果在另一个窗口中显示
(defun greeting (fname)
  (let ((yname (read-from-minibuffer "Enter your name: ")))
    (switch-to-buffer-other-window "*test*")
    (erase-buffer)
    (insert (format "Hello %s!\nI an %s." yname fname))
    (other-window 1)))
;;测试一下
(greeting "ShangHai")
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 我们将一些名字夏至列表中
(setq list-of-names '("aaa" "bbb" "ccc"))

;;用'cdr来取得第一个名字
(car list-of-names)

;;用'cdr取得剩下的名字
(cdr list-of-names)

;;用'push把名字添加到列表的开头
(push "Stephanie" list-of-names)

;;注意:'car和'cdr并不修改列表本身，但是'push却会对列表本身进行操作.
;;这个区别是很重要的:有些函数没有任何副作用,但还有一些却是有的

;; 我们对'list-of-names列表中的每一个元素都使用Hello函数
(mapcar 'hello list-of-names)

;;将'greeting改进，使得我们能够对'list-of-names中的所有名字执行。
(defun greeting()
  (switch-to-buffer-other-window "*test*")
  (erase-buffer)
  (mapcar 'hello list-of-names)
  (other-window 1))
;; 测试
(greeting)
;; 记得我们之前定义的'hello函数吗?这个函数接受一个参数,名字。
;;'mapcar调用 hello函数,并将'list-of-names作为参数先后传给'hello

;;现在我们对显示的buffer中的内容进行一些更改
(defun replace-hello-by-bonjour ()
  (switch-to-buffer-other-window "*test*")
  (goto-char (point-min))
  (while (search-forward "Hello" nil t)
    (replace-match "Bonjour"))
  (other-window 1))

;;(goto-char (point-min)) 将光标移到buffer的开始
;;(search-forward "Hello") 查找字符串"Hello"
;;(while x y) 当x返回某个值时执行y这个s式
;;当x返回 'nil(空),退出循环
(replace-hello-by-bonjour)

;;这个函数使用了're-search-forward:
;;和查找一个字符串不同,你用这个命令可以查找一个模式,即正则表达式

;;正则表达式"Bonjour \\(.+\\)!"的意思是:
;;字符串"Bonjour",之后跟着
;;一组        |  \\(... \\)结构
;;任意字符    |  .的含义
;;任意字符    |  +的含义
;;之后跟着"!"这个字符串
(defun boldify-names ()
  (switch-to-buffer-other-window "*TEST*")
  (goto-char (point-min))
  (while (re-search-forward "Bonjour \\(.+\\)!" nil t)
    (add-text-properties (match-beginning 1)
			 (match-end 1)
			 (list 'face 'bold)))
  (other-window 1))

;;测试一下
(boldify-names)


