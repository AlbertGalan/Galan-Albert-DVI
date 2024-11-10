# Galan-Albert-DVI
 
Primerament vaig tenir amb es problemes amb ses imatges que volia posar es objectes
i me varen proposar posar-ho amb import code, no ho vaig fer al moment
perque m'estimava més fer-ho quan ja tengués totes ses imatges.

Després vaig tenir problemes amb sa llibreria vjcl i amb la llibreria
vlc.dll, i al final el que hem fallava era que el vlc que tenia descarregat
era una versió de 32 bits i havia de tenir la versió de 64 bits pq funcionàs.


Seguidament he tengut problemes perquè es reproduísin els videos, i he
posat una clase windowOpened i al final m'ha anat correctament la reproducció
dels videos.


Llavors he tengut problemes amb com volía que es mostrés el FramePrincipal
així que he optat per fer-hi JPanels que es facin visibles o no, segons
si la sessió s'ha iniciat.

Després he tengut problemes amb fer lo de les funcionalitats a un mateix
Frame així que he optat per fer-ho amb diferents.

He fet un trainer_id amb un int, que servirà perquè quan inicii sessió s'asocii a la id d'usuari
corresponent al instructor, perque serveixi com a foreign key a l'hora de modificar-hi les reviews
de les taules de la base de dades, ja que s'ha de posar quin instructor ha fet el canvi amb una Id
reviewer que es la foreign key, i que s'omplirà amb el trainer_id, que s'associi al usuari.

Al final he arreglat lo de ses imatges ficant la ruta relativa amb el codi corresponent.

He tengut problemes amb el LogIn Inicial quan tanc la finestra del LogIn no puc tornar a obrir-lo
degut a que per alguna raó abans m'obria el Dialog més d'una vegada i vaig haver de posar un control 
de que només l'obrís un pic, pero no m'ha anat bé solucionar-ho

He afgeit audios als botons amb les llibreries de AudioInputStream i Clip.