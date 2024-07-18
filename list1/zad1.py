#Kornel Orawczak 346010
from enum import Enum
import math

class Figure(Enum):
    triangle = 1
    circle = 2
    square = 3

def new_triangle(x1, y1, x2, y2, x3, y3):
    return (Figure.triangle, ([x1,y1], [x2,y2], [x3,y3]))

def new_circle(x, y, r):
    return (Figure.circle, [x, y, r]) # x,y are coordinates of the circle's center 

def new_square(x, y, a):
    return (Figure.square, [x, y, a]) # x,y are coordinates of square's left down vector

def field(fig):
    ftype, params = fig

    if ftype == Figure.triangle:
       combinations = [(0,1), (1,2), (0,2)]
       a, b, c = [math.sqrt((params[comb[0]][0]-params[comb[1]][0])**2 + \
                            (params[comb[0]][1] - params[comb[1]][1])**2) \
                                for comb in combinations] 
       if a+b<=c or a+c<=b or b+c<=a:
           raise ValueError("Triangle with that coordinates doesn't exist!")
       p = (a+b+c)/2
       return round(math.sqrt(p*(p-a)*(p-b)*(p-c)),2)
    
    elif ftype == Figure.circle:
        return round(2*math.pi*params[2],2)
    
    elif ftype == Figure.square:
        return round(params[2]**2,2)
    
def move(fig, x, y):
    ftype, params = fig

    if ftype == Figure.triangle:
        for i in range(3):
            params[i][0]+=x
            params[i][1]+=y
    else:
        params[0]+=x
        params[1]+=y

def show(fig):
    ftype, params = fig
    
    if ftype == Figure.triangle:
        print(f"Inputted figure is a triangle with coordinates \
({params[0][0]},{params[0][1]}), \
({params[1][0]},{params[1][1]}), \
({params[2][0]},{params[2][1]})")

    elif ftype == Figure.circle:
        print(f"Inputted figure is a circle with a center at ({params[0]},{params[1]}) and radius = {params[2]}")

    elif ftype == Figure.square:
        print(f"Inputted figure is a sqaure placed at ({params[0]},{params[1]}) and its side's length = {params[2]}")

def field_sum(figs):
    return round(sum(field(fig) for fig in figs),2)




test_triangle = new_triangle(5,1,1,4,0,0)
test_circle = new_circle(5,6,4)
test_square = new_square(-1,3,4)
print(field(test_triangle))
print(field(test_circle))
print(field(test_square))
show(test_triangle)
move(test_triangle, 2, 3)
show(test_triangle)
show(test_circle)
move(test_circle, -5, 0)
show(test_circle)
print(field_sum([test_circle, test_square, test_triangle]))
