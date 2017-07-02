-- Log viewer SQL definitions

-- :name q-get-all-comments :?
select c.id as id,
       c.body as body,
       c.date as date,
       u.name as user,
       c."circle-id" as circle
  from comments as c
     inner join users as u
             on c."user-id" = u.id;

-- :name q-get-all-ideas :?
select i.id as id,
       i.name as name,
       count(i.id) as votes,
       group_concat(u.name) as users
  from users_ideas as ui
  inner join ideas as i
          on ui."idea-id" = i.id
  inner join users as u
          on ui."user-id" = u.id
    group by i.id;

-- :name q-get-all-votes
select i.id as "idea-id",
       i.name as "idea-name",
       u.name as user
 from users_ideas as ui
 inner join ideas as i
         on ui."idea-id" = i.id
 inner join users as u
         on ui."user-id" = u.id;

-- :name q-get-all-circles
select id from circles;
