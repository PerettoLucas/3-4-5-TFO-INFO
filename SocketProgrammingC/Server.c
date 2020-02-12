#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <netdb.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>


void main()
{
  //Socket erstellen
  int sock = socket(AF_INET, SOCK_STREAM, 0);

  int value = 1;
  setsockopt(sock, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &value, sizeof(value));


  //an Port binden
  struct sockaddr_in addr;
  addr.sin_family = AF_INET;
  addr.sin_addr.s_addr = INADDR_ANY; //oder inet_pton(..)
  addr.sin_port = htons(12345);
  bind(sock, &addr, sizeof(addr));


  //TCP listening socket starten
  int backlog = 5; //maximale Anzahl der nicht akzeptierten Verbindungen

  const int FLAGS = 0;
  char buffer[1024];


  while (1)
  {
      printf("Listening for Connection....\n");
      listen(sock, backlog);

      struct sockaddr clientaddr;
      socklen_t clientaddrlen;

      int clientsock = accept(sock, &clientaddr, &clientaddrlen);
      printf("Accepted Connection...\n");

      if(fork() == 0)
      {
        // handle client...

        //receive
        int bytesread = recv(clientsock, buffer, sizeof(buffer),FLAGS);
        if(bytesread <= 0)
          return; //socket was closed

        printf("%s", buffer);

        //Sending to the Client
        send(clientsock, buffer, strlen(buffer), FLAGS);


      }else
      {
        printf("Error forking the Child\n");
        exit(1);
      }


  }

}
