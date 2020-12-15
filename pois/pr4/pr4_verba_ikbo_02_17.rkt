#lang racket
;1
(define x1 (list 2 4 8 2 (list 2 4(list 8 2) 2)))
(define sum 0)
(define (lst_sum lst)
  (for ([rsum (in-list lst)])
    (if (list? rsum)
        (lst_sum rsum)
        (set! sum (+ sum rsum))
        )
    )
  )
(lst_sum x1)
(displayln "Задание 1 ")
(display "Сумма всех числовых элементов списка: ")
(display sum)
(displayln "") 


;2
(define result (list))
(define (reverse_list lst)
  (set! result (append result (reverse lst)))
  (when (> (length lst) 1)
    (reverse_list (cdr lst)))
  )
(define x2 (list 1 2 3 4 5 6))

(reverse_list x2)
(displayln "") 
(displayln "Задание 2 ")
(displayln "Итоговый список: ")
(write result)
(displayln "") 

;3
(define result3 (list))
(define (mul_lst lst)
  (define mult 1)
  (for ([e (in-list lst)])
    (set! mult (* mult e)))
   (set! result3 (append result3 (list mult)))
    (when (not (empty? (cdr lst)))
      (mul_lst (cdr lst)))
    )

(define x3 (list 1 2 3 4 5 6))

(mul_lst x3)
(displayln "") 
(displayln "Задание 3 ")
(displayln "Итоговый список: ")
(write result3)
  
 