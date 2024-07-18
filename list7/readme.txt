Kornel Orawczak 346010 Lista7 Programowanie Obiektowe

Zapis i odczyt z pliku został zmodyfikowany dla większej funkcjonalności operowania z plikami, mianowicie pliki odpowiednio 'cars.dat' oraz 'planes.dat' mogą zawierać wiele obiektów odpowiednich klas i edytory graficzne są przystosowane do dodawania wielu obiektów na raz, jak i odczytywania wielu obiektów jednocześnie. Poniżej załączam krótkie instrukcje. Program przetestowałem, o czym świadczy zawartość plików 'cars.dat' oraz 'planes.dat'

Zapisywanie i odczytywanie z pliku działa w moim programie w następujący sposób:
 
Aby dodać nowy obiekt do zewnętrznego pliku i zapisać go trwale, należy:
- wprowadzić dane w odpowiednie pola w edytorze graficznym 
- kliknąć przycisk "Create Object" 
- nacisnąć przycisk "Save to File"

Dopiero teraz stworzone obiekty nadpisane zostają do odpowiedniego pliku zewnętrznego (oczywiście można przed zapisem do pliku stworzyć więcej obiektów i zostaną dodane naraz). Następnie aby odczytać dane z pliku tekstowego lub je usunąć z niego klikamy odpowiednie przyciski. 

Warto dodać również, że zamknięcie edytora graficznego nie spowoduje usunięcia danych dodanych i obecnych w plikach tekstowych. Próba odczytu obiektów z pustego pliku nie wywali programu graficznego, lecz wypisze stosowne komunikaty błędu na standardowe wyjście. 