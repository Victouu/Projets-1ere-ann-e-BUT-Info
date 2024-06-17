docker container prune
docker volume prune
docker stop sae103-forever
docker rm sae103-forever

version=$(cat config | egrep "VERSION" | cut -d "=" -f 2)

php gendoc-tech.php src1.c src2.c src3.c > doc-tech-$version.html
php gendoc-user.php > doc-user-$version.html

docker volume create sae103

docker run -d --name sae103-forever -v sae103:/work bigpapoo/clock:latest 
docker cp ./doc-user.html sae103-forever:/work/
docker cp ./doc-tech.html sae103-forever:/work/
docker container run --name doc-gen-user -v sae103:/work bigpapoo/sae103-html2pdf:latest "html2pdf doc-user.html doc-user.pdf"
docker container run --name doc-gen-tech -v sae103:/work bigpapoo/sae103-html2pdf:latest "html2pdf doc-tech.html doc-tech.pdf"
docker container cp sae103-forever:/work/doc-user.pdf ./DOC_UTILISATEUR.pdf
docker container cp sae103-forever:/work/doc-tech.pdf ./DOC_TECHNIQUE.pdf
docker exec sae103-forever ls ./work

# docker container cp sae103-forever:/work/sae103 ./stock