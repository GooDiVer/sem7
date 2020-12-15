#lang racket
#|1|#
(display "Вычисление объема куба.\n")
(display "Введите длину ребра (см) и нажмите клавишу <Enter> = ")
(define r (read))
(printf "Объем куба: ~a куб.см.\n" (expt r 3))


#|2|#
(display "Вычисление объема цилиндра.\n")
(display "Введите исходные данные:\n")
(display "Радиус основания (см) => ")
(define r2 (read))
(display "Высота цилиндра (см) => ")
(define h2 (read))
(printf "Объем цилиндра: ~a куб.см.\n" (* (* pi (expt r2 2)) h2))

#|6|#
(display "\nрасстояние до дачи (км): " )
(define distance (read))
(display "количество бензина на 100км: " )
(define consumption (read))
(display "цена бензина (1л): " )
(define price (read))
(display "стоимости поездки на дачу и обратно: ")
(display (*(/ distance 100) price consumption 2))