-- Log viewer SQL definitions

-- :name q-get-all-comments :?
select * from comments;

-- :name q-get-all-ideas :?
select * from ideas;


select i.id, i.name, count( cantidad de votos??? )
 from users_ideas as ui
 inner join ideas as i
         on ui."idea-id" = i.id
   group by i.id; -- ????
-- inner join users as u
--         on ui."user-id" = u.id;
