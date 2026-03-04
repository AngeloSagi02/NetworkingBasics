/*
 * clientStream.c
 *
 *  Modified on: October 23, 2024
 *      Author: Angelo Saginario 

/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define YOU 0    //YOU
#define OTHER 1    //OTHER
#define PROTOPORT  5193  	/* Default server port number */
#define LOCALHOST "127.0.0.1" 	/* Default server address */

void init_board();
int check_winner();
void print_board();
int read_choice(int sd2);
void mark_choice(int choice);

char     player = YOU;

char    board[] = {'0', '1', '2','3','4','5','6','7','8','9'};


int main(int argc, char *argv[]) {
  struct  sockaddr_in sad; /* Struct to host the transport address of the remote socket */
  int     sd;              /* Socket descriptor              	  */
  int     port;            /* Port number                  		  */
  int     n;               /* Number of read bytes                */
  int     choice;
  char    *host;
  int     termineted = 0;

  /* Create a socket for stream oriented communication */
  sd = socket(PF_INET, SOCK_STREAM, 0);
  if (sd < 0) {
      fprintf(stderr, "socket creation failed\n");
      exit(1);
  }
	
  /* Clean the memory area that will store the transport address of the remote socket (server) */
  memset((char *)&sad,0,sizeof(sad));
  
  /* Set the transport address of the remote socket (server)) */
  sad.sin_family = AF_INET;
  if (argc > 2) {
      port = atoi(argv[2]);
  } else {
      port = PROTOPORT;
  }
  if (port > 0)
      sad.sin_port = htons((u_short)port);
  else {
      fprintf(stderr,"bad port number %s\n", argv[2]);
      exit(1);
  }

  /* Check for the existence of an IP address on the command line */
  if (argc > 1) {
  	  host = argv[1]; /* if host argument is specified */
  } else {
  	  host = LOCALHOST;
  }

  sad.sin_addr.s_addr = inet_addr(host);

  /* Connect the local socket (sd) with the remote one identified by the transport address sad */
  if (connect(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
        fprintf(stderr,"connect failed\n");
        exit(1);
   }
   /* The response message is processed */
   /* Read the data from the network and write onto the stdout */
   
    print_board();
    do{
        choice = read_choice(sd);
        mark_choice(choice);
        termineted = check_winner();
        system("clear");
        print_board();
        player = !player;
    }while (!termineted);
    
    if (termineted == 1) printf("Hai perso\n");
    else printf("Hai vinto\n");
   
   close(sd); 
   exit(0);
}


void print_board(){
    printf("\n\n\n|  %c  |  %c  |  %c  |\n", board[1], board[2], board[3]);
    printf("|-----|-----|-----|\n");
    printf("|  %c  |  %c  |  %c  |\n", board[4], board[5], board[6]);
    printf("|-----|-----|-----|\n");
    printf("|  %c  |  %c  |  %c  |\n", board[7], board[8], board[9]);
}


int read_choice(int sd2) {
    int choice, n = 0, control = 0;
    if (player == YOU) {
        do
        {
            printf("\nInserisci la coordinata dove inserire X: ");
            scanf("%d", &choice);
            if (board[choice] == '0' || board[choice]  == 'X')
            {
                control = 1; 
                printf("\nNon puoi sovrascrivere la cella: ");
            }
            else control = 0;
        
        } while (control != 0);

        write(sd2, &choice, sizeof(int));
       
    } else if (player == OTHER) {
        printf("Aspetta la giocata dell'avversario\n");
        read(sd2, &choice, sizeof(int));
        printf("%d\n", choice);
    }
    return choice;
}

void mark_choice(int choice){
    if(player == OTHER)
    {
        board[choice] = '0';
    }
    else if(player == YOU)
    {
        board[choice] = 'X';
    }
    
}

int check_winner(){
    int i;
    for (i=1; i<=7; i=i+3)
    {
        if (board[i] == '0' && board[i+1] == '0' && board[i+2] == '0')
        {
            return 1;
        }
        
    }
    for (i=1; i<=3; i++)
    {
        if (board[i] == '0' && board[i+3] == '0' && board[i+6] == '0')
        {
            return 1;
        } 
    }
    if (board[1] == '0' && board[5] == '0' && board[9] == '0')
    {
       return 1;
    }
    if (board[3] == '0' && board[5] == '0' && board[7] == '0')
    {
       return 1;
    }

    for (i=1; i<=7; i=i+3)
    {
        if (board[i] == 'X' && board[i+1] == 'X' && board[i+2] == 'X')
        {
            return 2;
        }
        
    }
    for (i=1; i<=3; i++)
    {
        if (board[i] == 'X' && board[i+3] == 'X' && board[i+6] == 'X')
        {
            return 2;
        } 
    }
    if (board[1] == 'X' && board[5] == 'X' && board[9] == 'X')
    {
       return 2;
    }
    if (board[3] == 'X' && board[5] == 'X' && board[7] == 'X')
    {
       return 2;
    }
    return 0;

}
