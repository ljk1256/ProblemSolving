#i/bin/bash
today=$(date "+%Y%m%d")
git add -A
git commit -m $today
git push