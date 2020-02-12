#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <netdb.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
  //Socket erstellen
  int clientsock = socket(AF_INET, SOCK_STREAM, 0);

  //zum Vermeiden von "address already in use":
  int value = 1;
  setsockopt(clientsock, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &value, sizeof(value));

  //optional: an Port binden
  struct sockaddr_in addr;
  addr.sin_family = AF_INET;
  addr.sin_addr.s_addr = INADDR_ANY;
  addr.sin_port = htons(12345); //network-byte-order = big-endian


  bind(clientsock, &addr, sizeof(addr));

  //TCP: connect
  struct sockaddr_in serv_addr;
  memset(&serv_addr, '0', sizeof(serv_addr));
  serv_addr.sin_port = htons(7);
  serv_addr.sin_family = AF_INET;
  //TODO add argv adress possibility
  if(inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr) > 0)
  {
    int c = connect(clientsock, (struct sockaddr*)&serv_addr, sizeof(serv_addr));

    if(c != 0)
    {
      printf("Connection could not be established\n");
      exit(1);
    }
  }else
  {
    printf("Connection could not be established\n");
    exit(1);
  }

  const int FLAGS = 0;
  char buff[1024] = { 0 };


  //doing the echo action
  while (1)
  {


    fgets(buff,sizeof(buff),stdin);
    //send buffer
    send(clientsock, buff, strlen(buff), FLAGS);

    //receive
    int bytesread = recv(clientsock, buff, sizeof(buff), FLAGS);
    if(bytesread <= 0)
      return bytesread; //socket was closed

    //Printing result
    printf("%s", buff);

  }

  return 0;
}
//10.216.20.52 Schual IP
