#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <netdb.h>
#include <stdlib.h>

int main(int argc , char *argv[])
{
    //Socket erstellen
    int sock = socket(domain, type, protocol);

    //
    int value = 1;
    setsockopt(sock, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &value, sizeof(value));


    return 0;
}