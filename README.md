This project is a solution for the Hashira Placements coding assignment.  

1. The program first reads a JSON input file.  
2. Each root is stored with a `base` and a `value`.  
3. The root is converted into a decimal integer using the base information.  
4. Only the first **k roots** are selected, because k = m + 1 is sufficient for a polynomial of degree m.  
5. The polynomial is then constructed by multiplying factors of the form `(x - root)`.  
6. The program finally prints:
   - The polynomial in an expanded, human-readable form.  
   - The list of coefficients, from constant term to highest power of x. 

## Files in This Repository

- **HashiraPolynomial.java**  
- **input1.json**  
- **output1.txt**  
- **input2.json**  
- **output2.txt**
