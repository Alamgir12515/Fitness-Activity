package com.example.fitnessactivity.data

import com.example.fitnessactivity.R
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.models.VideoModel

object LocalData {

    fun getLegsExercises(): List<Exercise> {
        return listOf(
            Exercise(
                "Goblet squat: 3 sets of 12 reps",
                "Holding the weight or object in front of your chest, stand with feet just outside hip width. Drive hips back and then down, as if sitting in a chair.\nKeep chest up and focus on your hips breaking below your knee crease. Push into heels, squeeze glutes (your booty!), and stand back up.",
                R.drawable.leg_1
            ),
            Exercise(
                "Pendulum lunges: 3 sets of 10 reps on each leg",
                "Hold the weight or object in front of your chest. Lunge forward with right leg, keeping left leg stationary until the reps are done.\nKeeping your weight in your right heel, push out of heel to stand up and immediately step into a reverse lunge with right leg in the back.\nComplete all reps on one side before switching legs.",
                R.drawable.leg_2
            ),
            Exercise(
                "Romanian deadlifts: 3 sets of 12 reps",
                "Standing tall with feet under hips, hold weights by your sides in both hands (or hold onto the handle of a broom or mop like a barbell). Bend knees slightly, and then hinge at your hips.\nKeeping core engaged, drive hips back until you feel a stretch in your hamstrings. Squeeze glutes and return to standing.",
                R.drawable.leg_3
            ),
            Exercise(
                "Step-ups: 3 sets of 10 reps on each leg",
                "Find a stool, bench, or box you can step on (the first step of a staircase can work, too, but will be a little short). Hold one weight at your chest and lift right foot, placing it on the box.\nKeeping chest up and right shin vertical, push into right heel and step up onto the box. Maintain control as you return to the starting position.\nIf you don’t have a box or other object to step onto: Start kneeling, step your right foot forward, and stand up. Then return to the kneeling position.",
                R.drawable.leg_4
            ),
            Exercise(
                "Weighted hip bridges: 3 sets of 12 reps",
                "Lie on your back with knees bent and feet flat on the floor. You should be able to touch your heels with your fingertips.\nHold a dumbbell, kettlebell, or other item in front of your hips. Brace core, squeeze glutes, push into heels, and lift your hips until shoulders, hips, and knees are in a line. Slowly lower your hips back to the floor.",
                R.drawable.leg_5
            ),
        )
    }

    fun getBodyBuildingDataList(): List<VideoModel> {
        return listOf(
            VideoModel("Best 7 abs Exercise home workout", "https://youtu.be/CIvbfuSaHHM"),
            VideoModel("Biceps workout at Home with dumbbells", "https://youtu.be/QA53bEoirMk"),
            VideoModel(
                "BEST SHOULDER EXERCISE AT HOME // SHOULDERS WORKOUT | NO EQUIPMENT",
                "https://youtu.be/qF0kR6UdgPY"
            ),
            VideoModel("BEST 10 ABS EXERCISES HOME WORKOUT", "https://youtu.be/fFjHsF_LoDM"),
            VideoModel("BACK EXERCISE AT HOME // NO EQUIPMENT", "https://youtu.be/g1yGknY2AP8"),
            VideoModel("chest EXERCISE for MASS | chest workout", "https://youtu.be/bEsWt_N1Iwg"),
            VideoModel(
                "Legendary Ab Workout (10 Mins Shredded Abs Workout)",
                "https://youtu.be//-SDK6DnqHZ0"
            ),
        )
    }

    fun getWeightGainDataList(): List<VideoModel> {
        return listOf(
            VideoModel("One-Minute Fitness Challenge: Push-Ups", "https://youtu.be/O6M0BHb2jGc"),
            VideoModel(
                "Can't Do 10 Pull Ups? Start Doing THIS (30 Days Results)",
                "https://youtu.be/kCl0Lzb6gXk"
            ),
            VideoModel(
                "How To Do Perfect SQUAT | FITNESS SPECIAL | SQUATS For Beginners",
                "https://youtu.be/YaXPRqUwItQ"
            ),
            VideoModel(
                "How To Do A LUNGE | Lunges for BEGINNERS | FITNESS SPECIAL",
                "https://youtu.be/wrwwXE_x-pQ"
            ),
            VideoModel(
                "Women's Routines for Bench Press Weightlifting",
                "https://youtu.be/W0O3RWFum4c"
            ),
            VideoModel("How to Do Triceps Bench Dips", "https://youtu.be/0326dy_-CzM"),
            VideoModel("HOW TO DO CRUNCHES", "https://youtu.be/5ER5Of4MOPI"),
        )
    }

    fun getWeightLossDataList(): List<VideoModel> {
        return listOf(
            VideoModel("2 Minute Aerobics Routine", "https://youtu.be/TNmPqM6kdFc"),
            VideoModel(
                "10 minutes of jump rope every day will do this to your body",
                "https://youtu.be/xg_b5ryJqio"
            ),
            VideoModel("5 Plank Variations For A Flat Tummy!", "https://youtu.be/QYFse84LZNc"),
            VideoModel(
                "How to Do a Push-Up Correctly, Arm Exercise, Fit How To",
                "https://youtu.be/eFOSh8vpd6I"
            ),
            VideoModel(
                "an't Do 10 Pull Ups? Start Doing THIS (30 Days Results)",
                "https://youtu.be/kCl0Lzb6gXk"
            ),
            VideoModel(
                "How To Do Perfect SQUAT | FITNESS SPECIAL | SQUATS For Beginners",
                "https://youtu.be/YaXPRqUwItQ"
            ),
            VideoModel(
                "How To Do A LUNGE | Lunges for BEGINNERS | FITNESS SPECIAL",
                "https://youtu.be/wrwwXE_x-pQ"
            ),
        )
    }

    fun getExerciseByName(name: String, bodyPart: BodyPart): Exercise {
        val list = when (bodyPart) {
            BodyPart.Leg -> {
                getLegsExercises()
            }
            BodyPart.Chest -> {
                getChestExercises()
            }
            BodyPart.Shoulder -> {
                getShoulderExercises()
            }
            BodyPart.Back -> {
                getBackExercises()
            }
            BodyPart.Arms -> {
                getArmsExercises()
            }
            else -> {
                getAbsExercise()
            }
        }
        return list.first { it.name == name }
    }

    fun getAbsExercise(): List<Exercise> {
        return listOf(
            Exercise(
                "Squat jumps",
                "Stand with your feet slightly wider than your hips.\n" +
                        "Lower your body to squat down.\n" +
                        "Press up through your feet, engage your abdominals, and jump up explosively.\n" +
                        "Lift your arms overhead as you jump.\n" +
                        "Upon landing, lower yourself back down to the squatting position.\n" +
                        "Do 2 to 3 sets of 10 repetitions.",
                R.drawable.abs_1
            ),
            Exercise(
                "Reverse lunge knee-ups",
                "Start in a standing lunge with your left foot forward.\n" +
                        "Place your right hand on the floor next to your front foot and extend your left arm straight back.\n" +
                        "Explosively jump up to bring your right knee up as high as you can, lifting your left arm and dropping your right arm back and down.\n" +
                        "Upon landing, move back into the starting lunge position.\n" +
                        "Continue for 30 seconds.\n" +
                        "Then do the opposite side.",
                R.drawable.abs_2
            ),
            Exercise(
                "Burpees",
                "From standing, bend your legs to come into a squat position.\n" +
                        "Place your hands down on the floor as you jump your feet back into a plank position, keeping your spine straight.\n" +
                        "Lower your chest to the floor for one push-up.\n" +
                        "Jump your feet forward to the outside of your hands, coming into a squat.\n" +
                        "Explosively jump up and lift your arms overhead.\n" +
                        "Do 2 to 3 sets of 8 to 12 repetitions.",
                R.drawable.abs_3
            ),
            Exercise(
                "Clapping push-ups",
                "Start in a plank position.\n" +
                        "Do a regular push-up by lowering your body down toward the floor.\n" +
                        "As you press up, push hard enough to lift your hands and body as high off the ground as possible.\n" +
                        "Clap your hands together.\n" +
                        "Return to the starting position.\n" +
                        "Continue for 30 seconds.",
                R.drawable.abs_4
            ),
            Exercise(
                "Box jumps",
                "For this exercise, you’ll need a box or something to jump on that’s 12 to 36 inches high. To increase the intensity, you can do the exercise using one leg.\n" +
                        "From standing, squat down to jump onto the box with both feet.\n" +
                        "Lift your arms up as you jump to gain momentum.\n" +
                        "Jump up and backward off the box, gently landing with bent knees.\n" +
                        "Do 2 to 3 sets of 8 to 12 repetitions.",
                R.drawable.abs_5
            ),
        )
    }

    fun getChestExercises(): List<Exercise> {
        return listOf(
            Exercise(
                "Banded floor press",
                "No heavy weights needed here. This floor press isolates your chest and triceps while minimizing stress on your shoulders.\n" +
                        "Try it:\n" +
                        "Sit on the floor with knees pointing up and feet flat on the floor.\n" +
                        "Holding one handle (or one end of the band) in each hand, place the resistance band across your back, under your shoulders.\n" +
                        "Lie down on your back.\n" +
                        "With palms facing the ceiling, push up until your arms are straight.\n" +
                        "Gently return your arms to the floor.\n" +
                        "Try 10–12 reps.\n" +
                        "Tips:\n" +
                        "Keep those wrists straight to avoid injuries.\n" +
                        "Move your hands closer together at the top of the movement.",
                R.drawable.chest_1
            ),
            Exercise(
                "Resistance band fly",
                "Who doesn’t love a little twist on a classic? This exercise isolates your pectorals for a super-focused workout.\n" +
                        "Try it:\n" +
                        "Find something sturdy to wrap your resistance band around, like a pole or a fence post.\n" +
                        "Hold the handles or the ends of the band, facing away from the pole.\n" +
                        "Widen your stance. Hold arms out to the sides at chest level.\n" +
                        "Keeping a slight bend in elbows, pull your arms out in front of you.\n" +
                        "Return your arms to the wide position over a 3-second count.\n" +
                        "Try 8–12 reps." +
                        "\nTip: Keep your elbows below your shoulders.",
                R.drawable.chest_2
            ),
            Exercise(
                "Resistance band push-up",
                "It’s like a push-up but harder. This move works your chest and triceps to improve upper-body strength.\n" +
                        "Try it:\n" +
                        "Wrap the resistance band around your back, under your shoulders.\n" +
                        "Get into a plank position.\n" +
                        "Maintaining the plank position, lower your body until it’s just above the floor.\n" +
                        "Push back up. (You should feel the tension of resistance band at the top of the movement.)\n" +
                        "Try 8–12 reps.\n" +
                        "Tips:\n" +
                        "If you’re feeling brave, you can add a knee tuck for an extra core workout.\n" +
                        "You can add a side knee tuck, too, but it’s not for the faint of heart.",
                R.drawable.chest_3
            ),
            Exercise(
                "Straight-arm pulldown",
                "Strong back and shoulder muscles help support your chest during pressing movements.\n" +
                        "This exercise works your latissimus dorsi (aka lats) and serratus anterior muscles and helps with scapular (shoulder) stability.\n" +
                        "Try it:\n" +
                        "Secure the middle of the resistance band on something a little higher than your head. (Anything stable, like a door, will work — just make sure the door stays closed.)\n" +
                        "Grab the ends of the band and take a few steps back, keeping your feet hip-width apart.\n" +
                        "Lean torso forward and keep a slight bend in knees. Place arms at a 45-degree angle in line with your ears.\n" +
                        "Keeping arms straight, pull the band down to your thighs and squeeze your lats.\n" +
                        "Pause at the bottom, then slowly release.\n" +
                        "Try 10–12 reps.\n" +
                        "Tips:\n" +
                        "Try a resistance band door anchor. If you’ll be doing this move a lot, it’s definitely worth the small investment.\n" +
                        "Pull your shoulders slightly down and back and try not to let them roll forward or shrug up toward your ears throughout each rep.",
                R.drawable.chest_4
            ),
            Exercise(
                "Resistance band row",
                "Another great lat-focused move, this exercise also helps support great posture.\n" +
                        "Try it:\n" +
                        "Sit with your legs out in front of you (like a rowing position).\n" +
                        "Place the resistance band around your feet and grab both ends.\n" +
                        "Engage your shoulder blades by squeezing them together, then pull elbows back until hands are near your torso.\n" +
                        "Slowly return to the starting position and repeat.\n" +
                        "Try 10–14 reps.\n" +
                        "Tip: Sit up tall and straight by engaging your abs.",
                R.drawable.chest_5
            ),
        )
    }

    fun getShoulderExercises(): List<Exercise> {
        return listOf(
            Exercise(
                "Shoulder roll",
                "The role of the roll is to relax your shoulders and reduce upper-body tension. It’s also a great pre-workout stretch.\n" +
                        "To do a shoulder roll:\n" +
                        "Stand tall with a straight spine.\n" +
                        "Relax your arms and shrug shoulders toward ears.\n" +
                        "Move shoulders in a circular motion for 30 seconds.\n" +
                        "Repeat in the other direction.\n" +
                        "Repeat 2–4 times.\n" +
                        "Pro tip: Imagine there’s a string pulling your head straight up.\n",
                R.drawable.shoulder_1
            ),
            Exercise(
                "Shoulder pendulum swing",
                "This swing sesh is an awesome arm stretch. It can help increase your range of motion, giving you a smoother flow of motion.\n" +
                        "To do a shoulder pendulum swing:\n" +
                        "Support your right arm on a table or chair.\n" +
                        "Let left arm relax and hang down.\n" +
                        "Gently start to swing left arm.\n" +
                        "Start to make small circles.\n" +
                        "Gradually make the circles bigger.\n" +
                        "Reverse the direction after 1 minute.\n" +
                        "Repeat for a total of 4–6 minutes on both arms.\n" +
                        "Pro tip: You can sit or stand for this exercise. Do what feels the most comfortable.",
                R.drawable.shoulder_2
            ),
            Exercise(
                "Dumbbell shoulder press",
                "This shoulder press can increase your strength and range of motion.\n" +
                        "To do a dumbbell shoulder press:\n" +
                        "While standing or sitting, hold a dumbbell in each hand at shoulder height.\n" +
                        "Bend your elbows at a 90-degree angle.\n" +
                        "Extend through elbows as you lift the weights overhead.\n" +
                        "Slowly return to the starting position.\n" +
                        "Do 10–15 reps.\n",
                R.drawable.shoulder_3
            ),
            Exercise(
                "Reverse fly",
                "This exercise targets your upper back muscles. It’s an important strengthening exercise for folks who sit behind a computer a lot.\n" +
                        "To do a reverse fly:\n" +
                        "Sit or stand with knees bent and lean forward.\n" +
                        "Hold a dumbbell in each hand (this also works with resistance bands).\n" +
                        "Let arms hang down to your calves.\n" +
                        "Gently raise the weights out to the sides until elbows are at shoulder height.\n" +
                        "Slowly lower the weights back to the starting position.\n" +
                        "Do 10–15 reps.\n" +
                        "Pro tip: Don’t hunch or arch your back.",
                R.drawable.shoulder_4
            ),
            Exercise(
                "Standing row",
                "This upright exercise can improve strength in your traps, rhomboids, and biceps.\n" +
                        "To do a standing row:\n" +
                        "Grasp two dumbbells or a barbell.\n" +
                        "Get your hands in line with your thighs.\n" +
                        "Let the weights hang in front of you with your arms straight down.\n" +
                        "Turn your palms toward your body.\n" +
                        "Lift the weights straight up toward your chin and exhale.\n" +
                        "Pause for a moment.\n" +
                        "Return to the starting position.\n" +
                        "Do 10–15 reps.\n" +
                        "Pro tip: Opt for a light weight so you can ease into the motion.",
                R.drawable.shoulder_5
            ),
        )
    }

    fun getBackExercises(): List<Exercise> {
        return listOf(
            Exercise(
                "Resistance band pull-apart",
                "A great exercise to kick off your back workout, the resistance band pull-apart is simple but effective. Choose a resistance band that allows you to complete 1 to 2 sets of 15 to 20 reps with good form.\n" +
                        "Directions:\n" +
                        "Stand with arms extended. Hold a resistance band taut in front of you with both hands so the band is parallel to the ground.\n" +
                        "Keeping arms straight, pull band to chest by moving arms out to sides. Initiate the movement from your mid-back, squeeze shoulder blades together and keep spine straight. Slowly return to start and repeat.",
                R.drawable.back_1
            ),
            Exercise(
                "Lat pulldown",
                "You can complete a lat pulldown on a machine at the gym or with a resistance band. Pulling the weight from above your head down to your chest requires the lats, biceps, and even forearms to work, strengthening them all.\n" +
                        "Directions:\n" +
                        "If you’re using a machine, position pad so it’s touching thighs. Stand up and grab bar wider than shoulder-width apart, then sit back down.\n" +
                        "Begin to pull bar down toward chest, bending elbows and pointing them toward ground. Engage upper and mid-back throughout the move. Keep torso straight, and don’t allow yourself to lean backward.\n" +
                        "Complete 1 to 3 sets of 12 reps.",
                R.drawable.back_2
            ),
            Exercise(
                "Back extension",
                "Back extensions target your core plus your whole posterior chain — in other words, the back side of your body. This makes them great for strengthening the erector spinae muscles and the entire lower back in general.\n" +
                        "Directions:\n" +
                        "Lie down on an exercise ball with abdomen on the center of ball. Press the balls of your feet into the ground behind you to stay balanced.\n" +
                        "Extend arms forward. Bend first at waist, then slowly raise upper body toward sky. Engage core and glutes and keep feet on floor.\n" +
                        "Pause for a moment at the top, then slowly lower down.\n" +
                        "Complete 1 to 3 sets of 12 reps.",
                R.drawable.back_3
            ),
            Exercise(
                "TRX row",
                "Using your body weight and requiring balance and stability, the TRX row is super-effective. The great thing about it is that it’s suitable for people of all ability levels.\n" +
                        "Directions:\n" +
                        "Grab TRX handles and walk under them, forming a tabletop position with arms extended. The more parallel your back is to the ground, the harder this exercise will be.\n" +
                        "Keeping back straight, row upward by pulling yourself toward ceiling. Keep elbows close to sides.\n" +
                        "Extend arms and return to start.\n" +
                        "Complete 1 to 3 sets of 12 reps.",
                R.drawable.back_4
            ),
            Exercise(
                "Wood chop",
                "A triple whammy for your core, arms, and back, the wood chop is a full-body movement. Use a dumbbell or medicine ball here — 10 pounds is a good place to start.\n" +
                        "Directions:\n" +
                        "Grab dumbbell or medicine ball with both hands. Hold it overhead with arms straight.\n" +
                        "Rotate hips to the left and bring dumbbell or ball down to the outside of left knee in a sweeping movement.\n" +
                        "On the ascent, twist trunk back toward the right and, keeping arms straight, bring dumbbell or\u2028ball back up above the right side of your head in an explosive but controlled movement. This movement should mimic a chopping motion, hence the name.\n" +
                        "Complete 12 reps on each side for 1 to 3 sets total.\n",
                R.drawable.back_5
            ),
        )
    }

    fun getArmsExercises(): List<Exercise> {
        return listOf(
            Exercise(
                "Arm slide",
                "Arm slides are great for activating your arms (especially your triceps), and they also work your entire core. According to the Mayo Clinic, core exercises like arm slides can improve your overall balance, stability, and body alignment.\n" +
                        "Equipment needed: Sliders, paper plates, or two small towels\n" +
                        "Kneel with your hands on both sliders. Place a mat under your knees to make this more comfortable, especially if you have sensitive knees or you’re on a hard floor.\n" +
                        "Engage your core by pulling your belly button toward your spine and tightening your abs.\n" +
                        "Keeping your spine straight and your core engaged, slowly slide your arms in front of you to get your chest close to the ground.\n" +
                        "Pull your arms back in, toward your knees, and return to your starting position without bending your elbows. Be careful to not arch your back as you pull your arms in. Throughout the movement, make sure you’re focused on keeping your core engaged and your back straight.\n" +
                        "Tips:\n" +
                        "You can make this easier by sliding each arm individually.\n" +
                        "You can also get all of the benefits without touching your chest to the ground. Simply go as low as you can, stopping before:\n" +
                        "you can no longer pull your hands back in with a straight spine\n" +
                        "before your chest touches the ground\n" +
                        "To make it more challenging, slide your arms from a plank and keep your knees off the ground throughout the exercise.",
                R.drawable.arms_1
            ),
            Exercise(
                "Ball slams",
                "This plyometric movement gives you all of the benefits without the pounding. Ball slams are a full-body movement that will fatigue your arms and add a little cardio to your workout.\n" +
                        "Equipment needed: Medicine ball or slam ball\n" +
                        "Stand with your feet hip-width apart and hold the ball at your chest.\n" +
                        "Lift the ball up and slightly behind your head.\n" +
                        "Bend your knees and actively throw the ball down on the ground as hard as you can.\n" +
                        "Catch the ball as it bounces back up (or scoop it up if it doesn’t bounce) and bring it back up over your head. All the while, keep your spine straight and use your knees to lift you back up.\n" +
                        "Begin your next repetition.\n" +
                        "Tips:\n" +
                        "Before you begin, test your ball to make sure it doesn’t bounce too aggressively. The ball should be relatively heavy, but not so heavy that you can’t perform the whole movement with your back straight.\n" +
                        "This movement should be done fluidly. Once you finish a rep, use the slight bounce of the ball to launch you into the next set. Do your best to keep moving through these repetitions to keep your heart rate up and the movements fluid.\n" +
                        "Start with as many reps as possible in 20 to 30 seconds for three to five sets. Be sure to take adequate rest between sets.\n" +
                        "Stop when you’re fatigued and can no longer safely hold the ball over your head or keep your spine straight throughout the movement.",
                R.drawable.arms_2
            ),
            Exercise(
                "Dumbbell bench press",
                "You don’t have to lift huge weights to get the benefits of a bench press.\n" +
                        "Doing a dumbbell bench press challenges your muscles and helps reduce muscle imbalances or weakness between your dominant and nondominant arms. While it’s best known for working your chest, the dumbbell bench press will also strengthen your deltoids, triceps, and lats.\n" +
                        "Equipment needed: Two dumbbells and a bench\n" +
                        "\n" +
                        "Lie with your back flat on the bench and feet firmly on the ground. If your feet don’t firmly touch the ground, place plates or a step bench under them to give you a stable position, or put your feet up on the bench.\n" +
                        "Keep your spine in a neutral position (your lower back should be slightly curved) by engaging your core.\n" +
                        "Pull your shoulder blades away from your ears and slightly together. Your shoulders, hips, and head should be firmly in contact with the bench.\n" +
                        "Keep your arms tight against your sides as you raise the dumbbells up. The palm of your hands should face forward throughout the movement or at a 45-degree angle.\n" +
                        "Slowly lower the dumbbells back to your chest with your elbows at your sides. Keep your elbows in tight throughout the entire movement to work your triceps.\n" +
                        "Tips:\n" +
                        "If you don’t have a bench available, you can do these on the floor or on a step bench.",
                R.drawable.arms_3
            ),
            Exercise(
                "Bicep curls with band",
                "Equipment needed: Resistance band\n" +
                        "Step on the band so it rests underneath the arch of your foot.\n" +
                        "Grab the ends of the band so your palms face forward and your arms are by your side.\n" +
                        "With your elbows tight against your ribs, slowly bend your arms to bring your hands to your shoulders.\n" +
                        "Slowly lower your hands back down to your sides.\n" +
                        "Tips:\n" +
                        "Don’t swing or lean back to bring your hands up. Your body should remain completely upright and still except for your arms.",
                R.drawable.arms_4
            ),
            Exercise(
                "TRX or supine barbell rows",
                "Not only will you work your arms with this exercise, but you’ll also strengthen those muscles of your upper back that help to improve posture.\n" +
                        "Equipment needed: TRX straps, low gymnastics rings, or an empty barbell and a rack.\n" +
                        "Share on Pinterest\n" +
                        "Grab the handles and slowly walk backward to get tension on the straps.\n" +
                        "With your chest facing the anchor point of the straps, walk your feet toward the straps until you’re at a 45-degree angle. Hold the straps so your palms face forward.\n" +
                        "Engage your core like you would in a plank position and keep your body in a straight line while you begin to pull your chest up to the handles. Keep your shoulder blades down, away from your ears, and pulled slightly together.\n" +
                        "Once your hands and chest meet, slowly lower back down to your starting position with your entire body in a straight line.\n" +
                        "Tips:\n" +
                        "Play around with your grip. Palms facing your feet will work your triceps. Palms facing your head will target your biceps.\n" +
                        "To make the rows easier, stand more upright by walking your feet closer to the anchor point. You should be upright enough that you can keep your hips and back straight throughout the entire movement without arching or bending your spine.\n" +
                        "If you want more of a challenge, walk your feet farther away from your hands.\n" +
                        "If you don’t have TRX straps or rings, you can use an empty barbell on a rack. Be careful to position your head under the rack so that you’re pulling the bar back into it instead of toward the front of the hooks. You can adjust the height of the bar to make it easier (up) or harder (down).",
                R.drawable.arms_5
            ),
        )
    }
}