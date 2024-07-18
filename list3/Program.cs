//Kornel Orawczak 346010 Visual Studio
using Lista3;
Lista<int> test_list = new Lista<int>();
test_list.print_list();
test_list.push_front(3);
test_list.push_front(5);
test_list.push_back(7);
Console.WriteLine(test_list.pop_back());
test_list.push_back(9);
test_list.print_list();
Console.WriteLine(test_list.pop_front());
test_list.print_list();
