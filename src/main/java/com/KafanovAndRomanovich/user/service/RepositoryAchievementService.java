package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Achievement;
import com.KafanovAndRomanovich.user.model.User;
import com.KafanovAndRomanovich.user.repository.AchievementRepository;
import com.KafanovAndRomanovich.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RepositoryAchievementService implements AchievementService {

    private static String BRONZEPOST="http://res.cloudinary.com/bbproject/image/upload/v1455917873/STUDENT_xfoyhv.png";
    private static String SILVERPOST="http://res.cloudinary.com/bbproject/image/upload/v1455917871/Graduate_a14phx.png";
    private static String GOLDPOST="http://res.cloudinary.com/bbproject/image/upload/v1455917873/Professor_joymin.png";
    private static String BRONZECOMMENT="http://res.cloudinary.com/bbproject/image/upload/v1455917869/beginer_xk8v1e.png";
    private static String SILVERCOMMENT="http://res.cloudinary.com/bbproject/image/upload/v1455917873/SKILLED_com_mgyykm.png";
    private static String GOLDCOMMENT="http://res.cloudinary.com/bbproject/image/upload/v1455917873/master_cjwzih.png";
    private static String WELCOME="http://res.cloudinary.com/bbproject/image/upload/v1455917872/welcome_coin_cw3rkx.png";
    private static String RATING="http://res.cloudinary.com/bbproject/image/upload/v1455917873/thumbup_ivqkzk.png";

    AchievementRepository achievementRepository;
    UserRepository userRepository;

    @Autowired
    public RepositoryAchievementService(AchievementRepository achievementRepository,UserRepository userRepository) {
        this.achievementRepository=achievementRepository;
        this.userRepository=userRepository;
    }



    //TODO refactor
    @Override
    public void addPostAchievement(User user) {
        if (user.getPosts().size() == 1) {
            for (Achievement achievement : user.getAchievements()) {
                if (achievement.getName().equals("FirstPost") ||
                        achievement.getName().equals("FivePosts") ||
                        achievement.getName().equals("TenPosts"))
                    return;
            }
            Achievement achievement = new Achievement();
            achievement.setName("FirstPost");
            achievement.setValue(BRONZEPOST);
            achievement.setDescription("Congratulations. You have added the first post");
            user.addAchievement(achievement);
            achievement.setUser(user);
            achievementRepository.save(achievement);
        } else if (user.getPosts().size() == 5) {
            for (Achievement achievement : user.getAchievements()) {
                if (achievement.getName().equals("FivePosts") ||
                        achievement.getName().equals("TenPosts"))
                    return;
            }

            Achievement achievement = getByName(user,"FirstPost");
            achievement.setName("FivePosts");
            achievement.setValue(SILVERPOST);
            achievement.setDescription("Congratulations. You have added five posts");
            achievementRepository.save(achievement);
        } else if (user.getPosts().size() == 10) {
            for (Achievement achievement : user.getAchievements()) {
                if (achievement.getName().equals("TenPosts"))
                    return;
            }

            Achievement achievement =getByName(user,"FivePosts");
            achievement.setName("TenPosts");
            achievement.setValue(GOLDPOST);
            achievement.setDescription("Congratulations. You have added ten posts");
            achievementRepository.save(achievement);
        }
    }
    @Override
    public void addCommentAchievement(User user) {
        if (user.getComments().size() == 1) {
            for (Achievement achievement : user.getAchievements()) {
                if (achievement.getName().equals("FirstComment") ||
                        achievement.getName().equals("FiveComments") ||
                        achievement.getName().equals("TenComments"))
                    return;
            }
            Achievement achievement = new Achievement();
            achievement.setName("FirstComment");
            achievement.setValue(BRONZECOMMENT);
            achievement.setDescription("Congratulations. You have added the first comment");
            user.addAchievement(achievement);
            achievement.setUser(user);
            achievementRepository.save(achievement);
        } else if (user.getComments().size() == 5) {
            for (Achievement achievement : user.getAchievements()) {
                if (achievement.getName().equals("FiveComments") ||
                        achievement.getName().equals("TenComments"))
                    return;
            }
            Achievement achievement = getByName(user,"FirstComment");
            achievement.setName("FiveComments");
            achievement.setValue(SILVERCOMMENT);
            achievement.setDescription("Congratulations. You have added five comments");
            achievementRepository.save(achievement);
        } else if (user.getComments().size() == 10) {
            for (Achievement achievement : user.getAchievements()) {
                if (achievement.getName().equals("TenComments"))
                    return;
            }
            Achievement achievement = getByName(user,"FiveComments");
            achievement.setName("TenComments");
            achievement.setValue(GOLDCOMMENT);
            achievement.setDescription("Congratulations. You have added ten comments");
            achievementRepository.save(achievement);
        }
    }
    public void addRegisteredAchievement(User user){
        Achievement achievement = new Achievement();
        achievement.setName("Welcome");
        achievement.setValue(WELCOME);
        achievement.setDescription("Congratulations. You have successfully signed up");
        user.addAchievement(achievement);
        achievement.setUser(user);
        achievementRepository.save(achievement);
    }

    public void addRatingAchievement(User user){
        for (Achievement achievement : user.getAchievements()) {
            if (achievement.getName().equals("Rating"))
                return;
        }
        if(user.getRatings().size()==5) {
            Achievement achievement = new Achievement();
            achievement.setName("Rating");
            achievement.setValue(RATING);
            achievement.setDescription("Congratulations. You are voted");
            user.addAchievement(achievement);
            achievement.setUser(user);
            achievementRepository.save(achievement);
        }
    }


    private Achievement getByName(User user,String name){
        List<Achievement> achievements=achievementRepository.findByUser(user);
        for (Achievement achievement :achievements) {
            if(achievement.getName().equals(name)) {
                return achievement;
            }
        }
        return null;
    }

}
