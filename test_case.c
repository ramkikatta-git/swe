#include<stdio.h>
#include<stdlib.h>
struct test_data
{
  char bool_data[10];
  char number[10];
  char dir_path[10];
  char double_data[10];
  char str_array[20][20];
};

struct test_data test_data_rec[] =
{
 /* Add all test data parameters here */
  {"true","23","/tmp","4546.5",{"red","green","blue"}},
  {"false","23","/tmp","6789.4",{"apple","orange","grape"}},
  {"abc","23","/tmp","78.3",{"cat","rat"}},
  {"1","23","/tmp","89.4",{"fish"}},
  {"0","23","/tmp","98.2"},
  {"-1","23","/tmp","88.3"},
  {" ","23","/tmp","97.4"},
  {" ","-23","/tmp","67.4"},
  {" ","23.59","/tmp","54.3"},
  {" ","2.3.59","/tmp","2.3"},
  {"True","259","/tmp","8.76"},
  {"True","259","/tmp","8.8.76"},
  {"True","259","/tmp","red"},
  {"True","259","/tmp","  "},
  {"True"," ","/tmp","89.9"},
};

int main()
{
   int ntests, i,j,len,main_len=0;
   char buffer[1024];
   ntests = sizeof(test_data_rec)/sizeof(test_data_rec[0]);
   for(i=0;i<ntests;i++)
   {
	main_len = sprintf(buffer,"%s%s%s%s%s%s%s%s%s","java -cp build/jar/args.jar com/cleancoder/args/ArgsMain ","-l ",test_data_rec[i].bool_data," -p ",test_data_rec[i].number," -d ",test_data_rec[i].dir_path," -f ",test_data_rec[i].double_data);
	len = 0;
	for(j=0;j<20;j++)
	{
	  if(strlen(test_data_rec[i].str_array[j]) > 0)
	  {
            len = len + sprintf((buffer + main_len + len),"%s%s"," -s ", test_data_rec[i].str_array[j]);
          }
	}
	printf("%s\n",buffer);
	system(buffer);
   }
}




