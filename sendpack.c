#include <stdlib.h>
#include <stdio.h>

#include <pcap.h>


int main(int argc, char **argv)
{   pcap_if_t *alldevs;
	pcap_if_t *d;
	int inum;
	int i=0;
	pcap_t *adhandle;
	pcap_t *fp;
	char errbuf[PCAP_ERRBUF_SIZE];
	u_char packet[100];


    //70-85-C2-25-3F-1E
	/* Open the adapter */
	if(pcap_findalldevs(&alldevs, errbuf) == -1)
	{
		fprintf(stderr,"Error in pcap_findalldevs: %s\n", errbuf);
		exit(1);
	}

	/* Print the list */
	for(d=alldevs; d; d=d->next)
	{
		printf("%d. %s", ++i, d->name);
		if (d->description)
			printf(" (%s)\n", d->description);
		else
			printf(" (No description available)\n");
	}

	if(i==0)
	{
		printf("\nNo interfaces found! Make sure WinPcap is installed.\n");
		return -1;
	}

	printf("Enter the interface number (1-%d):",i);
	scanf("%d", &inum);

	if(inum < 1 || inum > i)
	{
		printf("\nInterface number out of range.\n");
		/* Free the device list */
		pcap_freealldevs(alldevs);
		return -1;
	}

	/* Jump to the selected adapter */
	for(d=alldevs, i=0; i< inum-1 ;d=d->next, i++);

	/* Open the device */
	/* Open the adapter */
	puts(d->name);

	if ((adhandle= pcap_open_live(d->name,	// name of the device
//	if ((adhandle= pcap_open_live("{543CBA1D-806F-4638-8522-A5414741B692}",	// name of the device
		// name of the device
							 65536,			// portion of the packet to capture. It doesn't matter in this case
							 1,				// promiscuous mode (nonzero means promiscuous)
							 1000,			// read timeout
							 errbuf			// error buffer
							 )) == NULL)
	{
		fprintf(stderr,"\nUnable to open the adapter. %s is not supported by WinPcap\n", argv[1]);
		return 2;
	}
    printf("\nadhandle %d",adhandle);
	/* Supposing to be on ethernet, set mac destination to 1:1:1:1:1:1 */
	packet[0]=0xff;
	packet[1]=255;
	packet[2]=255;
	packet[3]=255;
	packet[4]=255;
	packet[5]=255;

	/* set mac source to 2:2:2:2:2:2 */
	packet[6]=2;
	packet[7]=2;
	packet[8]=2;
	packet[9]=2;
	packet[10]=2;
	packet[11]=2;
	packet[12]=0x43;
	packet[13]=0x21;
	packet[14]='c';
	packet[15]='i';
	packet[16]='a';
	packet[17]='o';
	packet[18]='o';


	/* Fill the rest of the packet */
	for(i=19;i<100;i++)
	{
		packet[i]= (u_char)i;
	}

	/* Send down the packet */
	if (pcap_sendpacket(adhandle,	// Adapter
		packet,				// buffer with the packet
		100					// size
		) != 0)
	{
		fprintf(stderr,"\nError sending the packet: %s\n", pcap_geterr(adhandle));
		return 3;
	}
	else{
        printf("\npacchetto mandato");
	}

	pcap_close(adhandle);
	return 0;
}

