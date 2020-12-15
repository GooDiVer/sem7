#lang racket
;1
(define (bubble-sort x1)
  (if (null? (cdr x1))
      x1
      (if (> (car x1) (cadr x1))
          (cons (cadr x1) (bubble-sort (cons (car x1) (cddr x1))))
          (cons (car x1) (bubble-sort (cdr x1)))
          )
      )
  )
(define (bubble N x1)
  (cond ((= N 1)(bubble-sort x1))
        (else (bubble (- N 1) (bubble-sort x1)))))

(define x '(7 6 5 4 3 2 1))
(bubble (length x) x)

;2
(define (quick-sort arr lt)
  (cond
    [(< 1 (length arr))
     (let ([pivot (first arr)]
           [gt (lambda (l r) (not (or (lt l r) (equal? l r))))])
       (append
        (quick-sort (filter (lambda (x) (lt x pivot)) arr) lt)
        (filter (lambda (x) (equal? x pivot)) arr)
        (quick-sort (filter (lambda (x) (gt x pivot)) arr) lt)))]
    [else arr]))
(quick-sort '(9 8 7 6 3 2 1) <)

;3
(define (parser expr)
  (cond
    ((null? expr) expr)
    ((list? expr)
     (let-values
         (((args op)
           (split-at-right expr 1)))
    (cons
     (car op) (map parser args))
       )
     )
    (else expr)))
(parser '( + 2 1))
