#lang racket
;1
(display "Радиус кольца (см): ")
(define k (read))
(display "Радиус отверстия (см): ")
(define o (read))
(display "Площадь кольца (кв.см): ")
(if (> k o )
    (display (* (- (* k k)(* o o)) 3.14))
    (display "Неверные данные"))


;3
(display "\nВведите год: ")
(define year (read))
(display year)
(if (or(>(modulo year 4)0)(and(=(modulo year 100)0)(>(modulo year 400)0)))
    (display " год - не високосный.")
    (display " год - високосный."))

;9
(display "\nВведите номер месяца (число от 1 до 12): ")
(define month (read))
(cond
   [(= month 1) (display "Январь")]
   [(= month 2) (display "Февраль")]
   [(= month 3) (display "Март")]
   [(= month 4) (display "Апрель")]
   [(= month 5) (display "Май")]
   [(= month 6) (display "Июнь")]
   [(= month 7) (display "Июль")]
   [(= month 8) (display "Август")]
   [(= month 9) (display "Сентябрь")]
   [(= month 10) (display "Октябрь")]
   [(= month 11) (display "Ноябрь")]
   [(= month 12) (display "Декабрь")]
   [else (display "Ошибка ввода данных")])