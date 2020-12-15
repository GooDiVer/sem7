#lang racket
(require rnrs/mutable-pairs-6)

;1
(define (sumlsts lst)
  (if (null? lst)
      0
      (if (list? (car lst))
          (+ (sumlsts (car lst)) (sumlsts (cdr lst)))
          (+ (car lst) (sumlsts (cdr lst))))))

(displayln "Данный список: ")
(writeln '(1 ((2 3) 4) 5 6))
(display "Сумма элементов: ")
(sumlsts '(1 ((2 3) 4) 5 6))

;2
(define findnth
  (lambda (lst place)
    (if (= place 0)
        (car lst)
        (findnth (cdr lst) (- place 1)))))

(display "Данный список: ")
(writeln (list 1 2 3 4 5))
(display "Найдем второй элемент в списке: ")
(findnth (list 1 2 3 4 5) 1)

;3
(define (last_element lst)
  (if (= (length lst) 1)
      (car lst)
      (last_element (cdr lst))))

(define (replace-nth list n item)
  (if (= n 0)
      (cons item (cdr list))
      (cons (car list) (replace-nth (cdr list) (- n 1) item))))

(define (swap-last-first lst)
  (letrec ([fe (car lst)]
           [le (last_element lst)]
           [fr (replace-nth lst 0 le)]
           [sr (replace-nth fr (- (length lst) 1) fe)])
    sr))

(define lst '(1 2 3 4 5))
(display "Исходный массив: ")
(writeln lst)
(display "Полученный массив: ")
(define res (swap-last-first lst))
(writeln res)