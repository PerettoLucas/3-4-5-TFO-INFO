#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <netdb.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>


int main()
{
  //Socket erstellen
  int sock = socket(AF_INET, SOCK_STREAM, 0);

  int value = 1;
  setsockopt(sock, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &value, sizeof(value));


  //an Port binden
  struct sockaddr_in addr = { 0 };
  addr.sin_family = AF_INET;
  addr.sin_addr.s_addr = INADDR_ANY; //oder inet_pton(..)
  addr.sin_port = htons(12335);
  bind(sock, (struct sockaddr*)&addr, sizeof(addr));


  //TCP listening socket starten
  int backlog = 5; //maximale Anzahl der nicht akzeptierten Verbindungen




  printf("Listening for Connection....\n");
  listen(sock, backlog);
  printf("Finished listening\n");

  struct sockaddr clientaddr;
  socklen_t clientaddrlen;

  while (1)
  {
      int clientsock = accept(sock, &clientaddr, &clientaddrlen);
      printf("Accepted Connection...\n");

      pid_t pid = fork();

      if(pid == 0)
      {
        // handle client...

        while (1)
        {
          const int FLAGS = 0;
          char buffer[1024] = { 0 };

          //receive
          int bytesread = recv(clientsock, buffer, sizeof(buffer),FLAGS);
          if(bytesread <= 0)
            return bytesread; //socket was closed

          printf("Empfangen :  %s", buffer);

          //Sending to the Client
          send(clientsock, buffer, strlen(buffer), FLAGS);
        }

      }else if(pid > 0)
      {
      }else {
        printf("Error forking the Child\n");
        exit(1);
      }


  }

}
