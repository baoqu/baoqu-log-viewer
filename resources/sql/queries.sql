-- Log viewer SQL definitions

-- :name q-get-all-comments :?
select * from comments;

-- :name q-get-all-ideas :?
select * from ideas;

-- :name q-get-all-votes
select i.id as "idea-id",
       i.name as "idea-name",
       u.name as user
 from users_ideas as ui
 inner join ideas as i
         on ui."idea-id" = i.id
 inner join users as u
         on ui."user-id" = u.id;
