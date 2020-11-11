

read -p 'Voulez vous ajouter les fichiers du dossier courant au dépot local y/n ?' ASW

while [ -z $ASW ] 
do

	echo "vide"
	read -p 'Voulez vous ajouter les fichiers du dossier courant au dépot local y/n ?' ASW
done



if [ $ASW = y ]
	then
		echo "Ajout des fichiers du dossier courant au dépot local"
		git add .
		
read -p 'Voulez vous commit les fichiers du dossier courant au dépot local y/n ?' ASW2	

while [ -z $ASW ] 
do

	echo "vide"
	read -p 'Voulez vous ajouter les fichiers du dossier courant au dépot local y/n ?' ASW
done



	if [ $ASW2 = y ]
	then
	
		read -p 'Message: ' MSG
		echo "Commit des fichiers du dossier courant au dépot local"
		git commit -m "$MSG"
		
	fi

		
		
	else
	
		echo "À bientôt..."
		exit
		
fi
