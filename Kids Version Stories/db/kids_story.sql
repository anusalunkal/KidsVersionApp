-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 20, 2024 at 08:23 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kids_story`
--

-- --------------------------------------------------------

--
-- Table structure for table `character_tbl`
--

CREATE TABLE IF NOT EXISTS `character_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `image` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `character_tbl`
--

INSERT INTO `character_tbl` (`id`, `name`, `image`) VALUES
(1, 'Spider-Man', 'spider.png'),
(2, 'Cindrella', 'cindrella.png'),
(3, 'Frozen', '2024-09-20-11-45-41download.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `plots_tbl`
--

CREATE TABLE IF NOT EXISTS `plots_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plot` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `plots_tbl`
--

INSERT INTO `plots_tbl` (`id`, `plot`) VALUES
(1, 'Forest'),
(2, 'Animals'),
(3, 'Fight'),
(4, 'Food');

-- --------------------------------------------------------

--
-- Table structure for table `story_tbl`
--

CREATE TABLE IF NOT EXISTS `story_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plot` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `story_desc` text NOT NULL,
  `story_image` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `story_tbl`
--

INSERT INTO `story_tbl` (`id`, `plot`, `title`, `story_desc`, `story_image`) VALUES
(1, 'Animals', 'The Town Mouse and The Country Mouse', 'On a sunny and pleasant day, a city mouse decided to visit one of his relatives who lived in the village.\r\n\r\nThe country mouse, who was very excited and happy to see the city mouse, brought out everything he had in his nest to entertain the city mouse! Wheat grains, grass stalks, roots, and delicious acorns! He also brought the city mouse some cool water to wash his hands and face!\r\n\r\nThe city mouse didnâ€™t eat much at all. He just tasted a little of the food! But he thought the food was very tasteless. So he frowned a little, but tried to be polite and not be rude.\r\n\r\nAfter dinner, the two mice chatted a lot. The city mouse talked enthusiastically about the attractions and benefits of the city, and the country mouse listened with excitement!\r\n\r\nThat night they slept soundly in the country mouseâ€™s nest until morning.\r\n\r\nThe country mouse, who really wanted to see everything the city mouse had told him about, when the city mouse asked him:\r\n\r\nâ€œWould you like to come to the city with me?â€\r\n\r\nHis eyes lit up and he said excitedly:\r\n\r\nâ€œOh, yes please!â€\r\n\r\nAfter a long journey and a lot of walking, they reached the city where the city mouse lived! They were very tired and hungry!\r\n\r\nThey went into a large living room with a table full of food! Jellies, cakes and very, very delicious cheeses! Foods that the country mouse had never even seen in his dreams!\r\n\r\nThe country mouse, who loved sweets, filled his paw with sweets, but as he was about to take a bite, he heard the meow of a cat scratching at the door!\r\n\r\nThe two little mice, who were very scared, went to a small shelter and had to stay there for a long time. They were too scared to breathe!\r\n\r\nAfter a long time, the city mouse looked out of the shelter and saw that the cat had gone! They were about to go back to the table and eat the delicious food when the cook came in to clean the table!\r\n\r\nAnd behind the cook was a big dog barking loudly and saying:\r\n\r\nâ€œWoof, woof, woofâ€\r\n\r\nThe country mouse, who was very scared and frustrated, quickly went to the city mouseâ€™s nest, packed his belongings into his small suitcase, and packed his bags! The country mouse said:\r\n\r\nâ€œExcuse me, but I have to go! Itâ€™s true that I donâ€™t have a big house or delicious food like you, but I prefer the simple, peaceful and safe life I have in the countryside to this chaos! A simple life is much better than luxurious troubles!â€\r\n\r\nAnd he ran away from there as fast as he could!', 'mousestory.jpg'),
(2, 'Animals', 'The Tortoise and the Hare', 'All the animals in the forest knew that the hare is the fastest among them. He could run through long roads without any hard effort.\r\n\r\nOne day, the hare saw a tortoise who was laboriously making his way in the forest. The hare stopped and started mocking the poor tortoise:\r\n\r\nOH! youâ€™ve got such short feet and a heavy shell! You must be the slowest animal in all the forest! I think itâ€™s possible for you to fall asleep when you are walking!\r\n\r\nThe tortoise was not offended by the hareâ€™s words. Slowly, she continued to put one foot in front of the other.\r\n\r\nI really have to look very closely to tell if you are moving or standing still. How long does it take you to walk ten yards? A whole day? The hare continued the rude laugh!\r\n\r\nAnd then he put his hands on his belly and burst out laughing. In that very moment, the tortoise stopped and smirked:\r\n\r\nGo ahead and make fun of me! Until this day, I have always managed to arrive at my destination. And when I think about it carefully, I even think that itâ€™s possible for me to beat you in a race!\r\n\r\nThe hare passed out from laughing when he heard this! After a few moments, when he finally succeeded to stop the laugh, he said:\r\n\r\nOK! I agree! So Iâ€™ll see you in competition day!\r\n\r\nThe two quickly agreed on the fox as the referee and met a day later by a brich tree. They decide to assign the starting point of the race to the tree. The fox announced.\r\n\r\nWhoever arrives first at the big pond has won the race! Are you ready? Three, two, one â€“ go!\r\n\r\nThe hair jumped as a lightning and disappeared after a few seconds. After a short time he had already finished half the race. He turned around and found that the tortoise had only took a few steps. He whispered:\r\n\r\nOk! Itâ€™s an easy one! I have got plenty of time left! I can go to the carrot field for a short trip.\r\n\r\nThen he hobbled straight to the big field and nibbled a few carrots with relish. When he returned, he saw that the tortoise is still close to the starting line. He was trying to walk with difficulty and make his way! He souted:\r\n\r\nOh you lazy! try harder! Come on!\r\n\r\nAll the birds and animals were cheering for the hare! Prompted by the applause of the audience, the hare performed several tricks and daredevil jumps.\r\n\r\nBut those carrots and doing tricks made him exhausted. So just before the finish line, he lay down in the grass and took a nap! And the nap turned into a deep sleep!\r\n\r\nSuddenly, he woke up from that sweet sleep by loud cheers and applause. He opened his eyes and could hardly believe what he saw. The turtle set foot over the finish line and had won the race.\r\n\r\nThe fox shouted:\r\n\r\nCongratulations, dear turtle. You have won the race!\r\n\r\nAll the animals of the forest congratulated the turtle and celebrated the incredible victory together with him!', 'tortoise.jpg'),
(3, 'Fight', 'The Two Goats', 'Once upon a time, there was a large river in a village that cut across a deep gorge. The townâ€™s people built a small, narrow bridge across the river to cross it now and then. The bridge being narrow, could only accommodate one person crossing it at a time.\r\n\r\nOne day a goat was crossing the bridge. At the other end of the bridge, he saw that there was another goat coming from the opposite direction. Since the bridge could only accommodate one person at a time, it was impossible for both the goats to cross it simultaneously.\r\n\r\nThe goats stood in their place and waited for the other to retreat. But neither of them was willing to back down to let the other pass. The first goat then said, â€˜I am the older goat, so you should allow me to pass first.â€™ The other goat denied and said, â€˜I am the stronger one, so I can cross the bridge faster. You will only amble along because you are old.â€™\r\n\r\nThe first goat took offence to what the other one said and continued, â€˜Although I am older, I am stronger than you.â€™ The second goat did not want to accept that, and both the goats soon got into a fight to prove to each other their strength.\r\n\r\nThe goats locked horns and fought fiercely on the narrow bridge. In no time, they lost their balance and fell into the river. The strong currents swept the goats away, causing them to drown and disappear into the deep waters.\r\n\r\nAfter the incident, two other goats faced one another in a similar situation. These goats also got into an argument about who should be the one to cross the bridge first. Just when it looked like things would take a turn for the worse, one of the goats stops the argument. He said, â€˜Stop! This bridge is too narrow for us to settle our dispute with a fight. If we continue this, we will both fall into the river and die instead. I have a plan.â€™\r\n\r\nThe wise goat went on to explain the plan. He said, â€˜I will lie down on the bridge while you walk over me. That way, we can both make it to the other end.â€™\r\n\r\nThe other goat understood the logic behind the idea and realised it was the sensible thing to do. He did exactly as the first goat instructed, and both of them made it across safely.', 'twogoats.jpg'),
(4, 'Food', 'The Great Cookie Adventure', 'Once upon a time in a cozy little town called Sweetville, there lived a curious girl named Lily. She loved two things more than anything else: exploring and cookies! One sunny Saturday, she decided to go on a special adventure to find the legendary Golden Cookie, said to be hidden in the Enchanted Cookie Forest.\r\n\r\nLily packed her backpack with a map, a flashlight, and her favorite snackâ€”chocolate chip cookies. As she stepped into the forest, she marveled at the giant candy trees with lollipop leaves and the soft, sugary ground that felt like marshmallows under her feet.\r\n\r\nAs she walked deeper into the forest, she met a friendly squirrel named Nutty. "Where are you going, Lily?" he asked, twitching his bushy tail.\r\n\r\n"I''m looking for the Golden Cookie!" she replied excitedly.\r\n\r\nNutty''s eyes sparkled. "I know where it is! But first, you must help me gather some ingredients for my famous Nutty Crunch Cookies!"\r\n\r\nLily agreed, and they set off together. They picked shiny acorns, plucked sweet berries, and even collected honey from a friendly bee named Buzz. After they gathered everything, Nutty showed Lily how to mix the ingredients together.\r\n\r\nWhile the cookies baked in a tiny oven made of twigs, Nutty told Lily about the Golden Cookie. "It can grant one wish to anyone who finds it!"\r\n\r\nWith their delicious cookies ready, they shared them with all the forest creatures. Everyone enjoyed the sweet treats, and Lily felt happy to see her new friends smile.\r\n\r\nAfter the cookies were gone, Nutty said, "Now letâ€™s find that Golden Cookie!" They followed a sparkling trail of cookie crumbs that led them to a shimmering clearing. There, on a pedestal made of gingerbread, sat the Golden Cookie, glowing in the sunlight.\r\n\r\nLily gasped in awe. She thought about her wish. â€œI wish for everyone in Sweetville to have plenty of cookies!â€ she exclaimed.\r\n\r\nThe Golden Cookie twinkled and then vanished in a puff of sparkling dust. Suddenly, cookies began to rain down from the sky! Chocolate chip, peanut butter, and even rainbow sprinkle cookies floated down, filling the air with the sweetest scent.\r\n\r\nLily and Nutty laughed and danced as the townsfolk of Sweetville rushed outside, their faces lighting up with joy. Everyone shared cookies, stories, and laughter that day, all thanks to Lilyâ€™s adventurous spirit and her wish for kindness.\r\n\r\nFrom that day on, Sweetville was known as the happiest town, where cookies were always shared, and adventures awaited at every corner. And Lily? She became the town''s official cookie explorer, ready for the next delicious journey!\r\n\r\nAnd so, the Great Cookie Adventure became a favorite story told by all, reminding everyone that sharing is the sweetest treat of all.', '2024-09-20-11-49-49download (1).jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `user_tbl`
--

CREATE TABLE IF NOT EXISTS `user_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `number` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user_tbl`
--

INSERT INTO `user_tbl` (`id`, `name`, `email`, `number`, `username`, `password`) VALUES
(1, 'Anu', 'anualunkal10@gmail.com', '0226360681', 'anualunkal', 'Anu@2024');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
