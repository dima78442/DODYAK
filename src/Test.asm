DATA Segment
STR db "new"
VD1 dd 0d7856fdh
VD2 dd 67ff89h
VD3 dd 89h
DD1 dd @M1
DD2 dd DD1
WW1 dw @M2
WW2 dw WW1
DATA ends
CODE1 Segment
    not ax
    not al
    not ah
    mov ax, VB1[bx]
    mov bh, VB1[di]
    mov bl, VB1[si]

    Call P1

    P1 Proc

    and bi, si
    and ah, bh
    and al, bl

    ret

    P1 endp

    Call far ptr P4

@LAB1:

mov VB1[bx], offset VD1

jna @LAB1

jna @LAB2

@LAB2:

mov VB1[di], offset VD2

CODE1 ends

CODE2 segment

P2 Proc far

mov VB1[bx], offset VD2

ret

P2 endp

Call P3

P3 Proc

ret

P3 endp

Code2 ends

END
