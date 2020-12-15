#lang racket
;4
(define (isPower2 x)
  (if ( = (modulo x 2) 0)
      (isPower2 (/ x 2))
      (if(= x 1)(display "степень 2\n")(display "не степень 2\n"))))

(display "Введите число: ")
(define x (read))
(isPower2 x)

;2
(define (last_element l)
  (cond ((null? (cdr l)) (car l))
         (else (last_element (cdr l)))))

(define (func x) (cond
                  [(integer? x) (sqrt x)]
                  [(list? x) (last_element x)]
                  [else x]))

(func "sdf")
(func 23)
(func (list 1 2 3 0))

;1
(define x '())
(define list1 (list -1 2 3 4))
(define list2 (list 0 1 -1 12))
(if (>(car list1) 0)
(set! x list1) 
(set! x (cons (cdr list2)(car list1))))
display x

