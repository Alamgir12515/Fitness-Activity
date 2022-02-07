package com.example.fitnessactivity.data

import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.models.VideoModel

object LocalData {
    //https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fabs_1.gif?alt=media&token=bbd63896-70d4-452a-b494-01ad65e89f53

    fun getLegsExercises(): List<Exercise> {
        return listOf(
            Exercise(
                "Goblet squat: 3 sets of 12 reps",
                "Holding the weight or object in front of your chest, stand with feet just outside hip width. Drive hips back and then down, as if sitting in a chair.\nKeep chest up and focus on your hips breaking below your knee crease. Push into heels, squeeze glutes (your booty!), and stand back up.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fleg_1.gif?alt=media&token=bbd954b4-a07d-4313-b1d7-50017154e64d"
            ),
            Exercise(
                "Pendulum lunges: 3 sets of 10 reps on each leg",
                "Hold the weight or object in front of your chest. Lunge forward with right leg, keeping left leg stationary until the reps are done.\nKeeping your weight in your right heel, push out of heel to stand up and immediately step into a reverse lunge with right leg in the back.\nComplete all reps on one side before switching legs.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fleg_2.gif?alt=media&token=b5a6be18-b7aa-4ed7-b07d-e008ff3f97f9"
            ),
            Exercise(
                "Romanian deadlifts: 3 sets of 12 reps",
                "Standing tall with feet under hips, hold weights by your sides in both hands (or hold onto the handle of a broom or mop like a barbell). Bend knees slightly, and then hinge at your hips.\nKeeping core engaged, drive hips back until you feel a stretch in your hamstrings. Squeeze glutes and return to standing.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fleg_3.gif?alt=media&token=127b0b40-3791-4861-9813-2ed75c406b16"
            ),
            Exercise(
                "Step-ups: 3 sets of 10 reps on each leg",
                "Find a stool, bench, or box you can step on (the first step of a staircase can work, too, but will be a little short). Hold one weight at your chest and lift right foot, placing it on the box.\nKeeping chest up and right shin vertical, push into right heel and step up onto the box. Maintain control as you return to the starting position.\nIf you don’t have a box or other object to step onto: Start kneeling, step your right foot forward, and stand up. Then return to the kneeling position.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fleg_4.gif?alt=media&token=58b53cbb-c5df-45b6-98ff-0532f4588017"
            ),
            Exercise(
                "Weighted hip bridges: 3 sets of 12 reps",
                "Lie on your back with knees bent and feet flat on the floor. You should be able to touch your heels with your fingertips.\nHold a dumbbell, kettlebell, or other item in front of your hips. Brace core, squeeze glutes, push into heels, and lift your hips until shoulders, hips, and knees are in a line. Slowly lower your hips back to the floor.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fleg_5.gif?alt=media&token=6c9c3a13-268f-4b58-8ecb-abcab6494cde"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fabs_1.gif?alt=media&token=bbd63896-70d4-452a-b494-01ad65e89f53"
            ),
            Exercise(
                "Reverse lunge knee-ups",
                "Start in a standing lunge with your left foot forward.\n" +
                        "Place your right hand on the floor next to your front foot and extend your left arm straight back.\n" +
                        "Explosively jump up to bring your right knee up as high as you can, lifting your left arm and dropping your right arm back and down.\n" +
                        "Upon landing, move back into the starting lunge position.\n" +
                        "Continue for 30 seconds.\n" +
                        "Then do the opposite side.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fabs_2.gif?alt=media&token=3dd53088-dfec-4919-ba9e-76f367ccf3d1"
            ),
            Exercise(
                "Burpees",
                "From standing, bend your legs to come into a squat position.\n" +
                        "Place your hands down on the floor as you jump your feet back into a plank position, keeping your spine straight.\n" +
                        "Lower your chest to the floor for one push-up.\n" +
                        "Jump your feet forward to the outside of your hands, coming into a squat.\n" +
                        "Explosively jump up and lift your arms overhead.\n" +
                        "Do 2 to 3 sets of 8 to 12 repetitions.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fabs_3.gif?alt=media&token=24ca8f3c-4659-4c1c-884d-60881fa359d9"
            ),
            Exercise(
                "Clapping push-ups",
                "Start in a plank position.\n" +
                        "Do a regular push-up by lowering your body down toward the floor.\n" +
                        "As you press up, push hard enough to lift your hands and body as high off the ground as possible.\n" +
                        "Clap your hands together.\n" +
                        "Return to the starting position.\n" +
                        "Continue for 30 seconds.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fabs_4.gif?alt=media&token=4babca44-3c62-4bc7-9ffc-03fc24a31b0d"
            ),
            Exercise(
                "Box jumps",
                "For this exercise, you’ll need a box or something to jump on that’s 12 to 36 inches high. To increase the intensity, you can do the exercise using one leg.\n" +
                        "From standing, squat down to jump onto the box with both feet.\n" +
                        "Lift your arms up as you jump to gain momentum.\n" +
                        "Jump up and backward off the box, gently landing with bent knees.\n" +
                        "Do 2 to 3 sets of 8 to 12 repetitions.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fabs_5.gif?alt=media&token=f8d91518-21fb-40f6-ab4e-2dccc8921ecb"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fchest_1.gif?alt=media&token=db3ae29d-687f-4eb2-b65b-75723db353b3"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fchest_2.gif?alt=media&token=424770cb-b153-4926-a384-2b4327ab6bba"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fchest_3.gif?alt=media&token=b17e7736-902d-4ecf-ac79-574c7cfc61a6"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fchest_4.gif?alt=media&token=cd9b2537-34ef-4135-beb8-f474002f5f50"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fchest_5.gif?alt=media&token=926be387-5e2f-4fbd-87ca-d3b034fe7c1d"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fshoulder_1.gif?alt=media&token=80150ad7-24d6-45f2-972d-e40a8fa194f3"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fshoulder_2.gif?alt=media&token=9594c353-2d64-4a58-9404-825261fc985c"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fshoulder_3.gif?alt=media&token=4188ee62-5b0a-49fd-b3fc-52e81da28594"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fshoulder_4.gif?alt=media&token=24b6df15-06e4-4a97-8f38-2c78b49e3e59"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fshoulder_5.gif?alt=media&token=ce3d2110-b28a-4817-a229-322ae86c9abe"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fback_1.gif?alt=media&token=1c6a1a10-72b0-4b1f-9c68-49ca68953d95"
            ),
            Exercise(
                "Lat pulldown",
                "You can complete a lat pulldown on a machine at the gym or with a resistance band. Pulling the weight from above your head down to your chest requires the lats, biceps, and even forearms to work, strengthening them all.\n" +
                        "Directions:\n" +
                        "If you’re using a machine, position pad so it’s touching thighs. Stand up and grab bar wider than shoulder-width apart, then sit back down.\n" +
                        "Begin to pull bar down toward chest, bending elbows and pointing them toward ground. Engage upper and mid-back throughout the move. Keep torso straight, and don’t allow yourself to lean backward.\n" +
                        "Complete 1 to 3 sets of 12 reps.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fback_2.gif?alt=media&token=3f317968-6131-4817-a67f-d09cb95e1678"
            ),
            Exercise(
                "Back extension",
                "Back extensions target your core plus your whole posterior chain — in other words, the back side of your body. This makes them great for strengthening the erector spinae muscles and the entire lower back in general.\n" +
                        "Directions:\n" +
                        "Lie down on an exercise ball with abdomen on the center of ball. Press the balls of your feet into the ground behind you to stay balanced.\n" +
                        "Extend arms forward. Bend first at waist, then slowly raise upper body toward sky. Engage core and glutes and keep feet on floor.\n" +
                        "Pause for a moment at the top, then slowly lower down.\n" +
                        "Complete 1 to 3 sets of 12 reps.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fback_3.gif?alt=media&token=9f3b70ee-5e36-4c44-96e3-364e280816da"
            ),
            Exercise(
                "TRX row",
                "Using your body weight and requiring balance and stability, the TRX row is super-effective. The great thing about it is that it’s suitable for people of all ability levels.\n" +
                        "Directions:\n" +
                        "Grab TRX handles and walk under them, forming a tabletop position with arms extended. The more parallel your back is to the ground, the harder this exercise will be.\n" +
                        "Keeping back straight, row upward by pulling yourself toward ceiling. Keep elbows close to sides.\n" +
                        "Extend arms and return to start.\n" +
                        "Complete 1 to 3 sets of 12 reps.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fback_4.gif?alt=media&token=9988eea6-1459-438b-8a08-7b7e409ca93d"
            ),
            Exercise(
                "Wood chop",
                "A triple whammy for your core, arms, and back, the wood chop is a full-body movement. Use a dumbbell or medicine ball here — 10 pounds is a good place to start.\n" +
                        "Directions:\n" +
                        "Grab dumbbell or medicine ball with both hands. Hold it overhead with arms straight.\n" +
                        "Rotate hips to the left and bring dumbbell or ball down to the outside of left knee in a sweeping movement.\n" +
                        "On the ascent, twist trunk back toward the right and, keeping arms straight, bring dumbbell or\u2028ball back up above the right side of your head in an explosive but controlled movement. This movement should mimic a chopping motion, hence the name.\n" +
                        "Complete 12 reps on each side for 1 to 3 sets total.\n",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Fback_5.gif?alt=media&token=59fcad45-97e0-44a7-a800-899f28cb07e3"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Farms_1.gif?alt=media&token=ddd87a8c-6fdf-44bb-aa03-d9ddbd8fd73e"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Farms_2.gif?alt=media&token=f6ea49a3-bede-40c5-8c8b-4cfdb363a253"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Farms_3.gif?alt=media&token=691d0013-b658-4561-b1cd-e4c1352f629a"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Farms_4.gif?alt=media&token=f0dacabf-5afc-42d3-8eb3-1876c7152ece"
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
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Exercises%2Farms_5.gif?alt=media&token=d6626fca-1539-4967-83bc-cb0f3a4d227d"
            ),
        )
    }

    fun gain(): List<Exercise> {
        return listOf(
            Exercise(
                "Squats",
                "Squats are one of the most basic lower-body exercises for toning and strengthening the lower body, and they're frequently used in weight-gain workouts. You may graduate to more harder variations by adding weights once you've mastered the basic squat form." +
                        "How to do it: \n" +
                        "Standing tall with your back straight and your feet slightly wider than hip-width apart is a good place to start. With your arms out in front of you, maintain a straight posture.\n" +
                        "Squeeze your glutes and move your butt backward rather than merely bending your knees.\n" +
                        "Start by inhaling, engaging your core, and bending your knees while pushing your butt out.\n" +
                        "Sit with your weight supported by your heels. Lower your hip joints till they are lower than your knees. Because that's exactly what a full squat is.\n" +
                        "Keep your hands in this posture for three seconds.\n" +
                        "Exhale and begin to stand up, keeping your weight on your heels.\n" +
                        "Repeat.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2Fsquats.gif?alt=media&token=3bc4dfc7-81a0-458c-8c4a-5f17e196f6df"
            ),
            Exercise(
                "Push Ups",
                "Push-ups are a great exercise to start with if you're a novice. When it comes to strengthening the upper body, it is one of the most essential workouts for weight growth. Push-ups, above all, help you build muscle in your arms and shoulders." +
                        "How to do it: \n" +
                        "Lie down on the ground, face down.\n" +
                        "Hands should be somewhat broader than shoulders.\n" +
                        "Push yourself up slowly until your arms are completely extended.\n" +
                        "Lower yourself to the point when your chest is almost touching the floor.\n" +
                        "Push yourself back up after a little pause.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2Fpushup.gif?alt=media&token=ddce9dab-5d46-4e24-b940-163b67518cd2"
            ),
            Exercise(
                "Lunges",
                "Lunges, like squats, aid in the bulking and toning of your leg and hip muscles. It's one of the most effective ways to gain weight. You may also add weights or attempt various lunge variations to make the workout more tough." +
                        "How to do it: \n" +
                        "Flex your abdominal muscles while standing up straight.\n" +
                        "Make a big step forward.\n" +
                        "Lower your body until your shin is vertical and your thigh is parallel to the floor.\n" +
                        "Return to your original position by pressing back on your heel.\n" +
                        "Rep with the opposite leg.\n",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2Flunges.gif?alt=media&token=2768ada8-0e16-4644-9952-46d708cbf994"
            ),
            Exercise(
                "Tricep Dips",
                "Tricep Dips are another simple arm and back workout that you may practise at home to gain weight. Dips might help you gain muscular growth in your upper body if done correctly. This workout can also aid in the development of strength for other exercises such as the bench press.\n" +
                        "How to do it: \n" +
                        "Sit at the chair's or bench's edge and grip the edges with your hands.\n" +
                        "Get out of your seat and drop your hips to the ground.\n" +
                        "Maintain the posture by applying pressure to your palms.\n" +
                        "Slowly return to your original seated posture.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2FTricep%20Dips.gif?alt=media&token=346c78f5-0828-4802-b26b-3cc7860f2915"
            ),
            Exercise(
                "Pull Ups",
                "Pull-ups, particularly with weights, are an excellent method to bulk up your muscles. If you're a novice, start with basic pull-ups and progress to weighted pull-ups as your strength improves. You may use a pull-up bar if you're practising this weight gain workout at home.\n" +
                        "How to do it: \n" +
                        "With your palms facing away from you and arms shoulder-width apart, grab the pull-up bar with both your hands.\n" +
                        "Pull yourself up to the point where your feet aren't touching the ground, and keep going until your chin is clear of the bar.\n" +
                        "Slowly lower yourself until your arms are straight once again.\n" +
                        "Repeat.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2FPull%20Ups.gif?alt=media&token=52f19d3e-670f-4c2f-8c28-8e97852951d5"
            ),
            Exercise(
                "Bench Press",
                "The bench press is an excellent technique to bulk up your chest muscles. However, like with any other exercise, it's critical to perform this weight-gain routine properly.\n" +
                        "How to do it: \n" +
                        "Begin by laying on the bench with the bar in your hands.\n" +
                        "Warm up with just the bar and no weights first, then add the weights.\n" +
                        "Before lowering the bar to your chest, hold it again and lock your elbows out.\n" +
                        "Lift the bar over your chest with your arms fully extended, taking a deep breath.\n" +
                        "Bring the bar to the original position and repeat. ",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2FBench%20Press.gif?alt=media&token=c8aa29f3-0097-4708-97f6-1419afe7c64c"
            ),
            Exercise(
                "Dumbbell Overhead Press",
                "Overhead presses will help you gain muscular mass in your entire body. Not only can this exercise improve your posture, but it will also help you create strong back muscles.\n" +
                        "How to do it: \n" +
                        "Maintain a straight back and stand tall.\n" +
                        "While inhaling, hold a dumbbell in each hand and elevate the weights above your head in a smooth manner.\n" +
                        "While breathing, hold the posture and return to the beginning position.\n" +
                        "Repeat.\n",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2FDumbbell%20Overhead%20Press.gif?alt=media&token=0837a218-e31c-4fbc-b6de-4049dc77564b"
            ),
            Exercise(
                "Crunches",
                "You're probably thinking that crunches help you lose belly fat, and you're right. But first, let me tell you something you don't know! Crunches can also help you gain core strength and lean muscular mass, resulting in a more appealing stomach.\n" +
                        "How to do it: \n" +
                        "With your knees bent and your feet flat on the floor, lie flat on a carpet or your yoga mat.\n" +
                        "Inhale while crossing your arms over your chest and contracting your abs.\n" +
                        "Exhale and elevate your upper body while maintaining a relaxed head and neck.\n" +
                        "Return to the starting position by inhaling.\n" +
                        "Repeat.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2FCrunches.gif?alt=media&token=fb98d75a-7ad1-4f37-94c4-7c22d96ca53c"
            )
        )
    }

    fun loss(): List<Exercise> {
        return listOf(
            Exercise(
                "Forward Lunge",
                "A. Stand tall with feet hip-width apart. Place hands on hips or hold weights by sides to start.\n" +
                        "B. Take a controlled step forward with the right leg. Keeping spine tall, lower body until the front and back leg form a 90-degree angle.\n" +
                        "C. Pause, then step right leg back to start. Step left leg forward to repeat on the other side.\n" +
                        "Sets: 3\n" +
                        "Reps: 10 per side\n\n" +
                        "Tips:\n" +
                        "There are many variations to the lunge, but the classic forward lunge is still very effective for weight loss, as it works multiple muscles at once (think: glutes, quads, and hamstrings).",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Loss%2FForward%20Lunge.gif?alt=media&token=0c8baaf8-3911-4812-9a00-05ec5b28825c"
            ),
            Exercise(
                "Burpee",
                "A. Stand with your feet shoulder-width apart and arms at your sides. Push your hips back, bend knees, and reach palms to the ground to lower into a crouch.\n" +
                        "Immediately lower back into a squat for the next rep. Repeat 8 to 12 times. Complete 3 sets.\n" +
                        "B. With hands shoulder-width on the floor directly in front of feet, and shift your weight to them to jump back and land softly in plank position.\n" +
                        "C. Jump feet forward so they land just outside of hands. Jump explosively into the air, reaching hands overhead or leaving by sides.\n" +
                        "Sets: 3\n" +
                        "Reps: 8 to 12\n" +
                        "Tips:\n" +
                        "This exercise effectively targets your core, chest, and legs simultaneously. Feel the burn and know you're building lots of lean muscle.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Loss%2FBurpee.gif?alt=media&token=a6abf5f9-6ac1-4a7a-9cd5-e0c5d0cee64f"
            ),
            Exercise(
                "Explosive Lunge",
                "A. Start with feet together, hands on your hips. Step forward with the right leg and lower into a lunge so right knee is bent at a 90-degree angle.\n" +
                        "B. Jump up, switching legs midair.\n" +
                        "C. Land softly with the left leg forward, immediately lowering into a lunge. \n" +
                        "Sets: 3\n" +
                        "Reps: Repeat for 1 minute",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Loss%2FExplosive%20Lunge.gif?alt=media&token=c6e8c7e5-687f-4164-9da9-1d983db21f55"
            ),
            Exercise(
                "Squat",
                "A. Start with feet hip-width apart, arms either at sides holding weights or clasped in front of chest.\n" +
                        "B. Keeping weight in heels and back straight, sit hips back and bend knees to lower into a squat until thighs are parallel to the floor. Remember to keep knees in line with toes the entire time. Maintain an even pace and rise back to start.\n" +
                        "Sets: 3\n" +
                        "Reps: 15\n" +
                        "Tips:\n" +
                        "Squats are one of the best exercises for weight loss and for building overall strength. When you do them correctly, you engage your core and entire lower body.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Gain%2Fsquats.gif?alt=media&token=3bc4dfc7-81a0-458c-8c4a-5f17e196f6df"
            ),
            Exercise(
                "Double Jump",
                "A. Stand with feet slightly wider than hip-width apart and lower into a deep squat.\n" +
                        "B. Rise up as if you're jumping, but land in a lunge position with your right leg back.\n" +
                        "C. Use momentum to jump from this lunge position back to a squat. Then repeat, landing in a lunge on the opposite side.\n" +
                        "Sets: 2\n" +
                        "Reps: Repeat for 45 seconds\n" +
                        "Tips:\n" +
                        "Take your traditional squats up a notch by incorporating a jump and lunge. The movement will increase your heart rate and you'll feel the burn in your abs, butt, and legs.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Loss%2FDouble%20Jump.gif?alt=media&token=b0b13da5-85c9-4bd2-8f1b-9ca6c8953958"
            ),
            Exercise(
                "Mountain Climbers",
                "A. Start in a plank position on the floor. Drive the right knee in toward chest without raising hips or allowing right foot to touch the floor. \n" +
                        "B. Place right foot back in plank and repeat on the other side, driving the left knee in toward chest. Repeat, alternating legs.\n" +
                        "Sets: 3\n" +
                        "Reps: Repeat for 1 minute\n" +
                        "Tips:\n" +
                        "Mountain climbers are an excellent way to burn calories. The quick leg motion targets obliques, butt, and hamstrings.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Loss%2FMountain%20Climbers.gif?alt=media&token=1597bcf8-a7b4-40f0-adeb-c8d7530f87af"
            ),
            Exercise(
                "Jump Rope",
                "Start with feet together, hands holding ends of the jump rope, elbows in toward ribs. Swing the jump rope and step or hop both feet over. Don't jump in between, just jump with each swing of the rope.\n" +
                        "Sets: 3\n" +
                        "Reps: Repeat for 1 minute\n" +
                        "Tips:\n" +
                        "Jumping rope is a great total-body tool made for weight loss. Challenge yourself to complete a full minute of jumping—it's harder than you think. \n" +
                        "Check the length of the jump rope by holding it in each hands and ensuring the handles line up with shoulders.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Loss%2FJump%20Rope.gif?alt=media&token=931b8219-bb13-4718-b63a-d66ac42f8f13"
            ),
            Exercise(
                "Kettlebell Swing",
                "A. Stand with feet slightly wider than hip-width apart and a kettlebell slightly in front of feet. Grasp kettlebell handle with both hands. Keeping back straight, hinge at the hips to hike the kettlebell backward between legs.\n" +
                        "B. Press hips forward to stand and swing the kettlebell overhead, keeping core engaged. Allow the kettlebell to fall forward and between legs to begin the next swing.\n" +
                        "Sets: 3\n" +
                        "Reps: 15\n" +
                        "Tips:\n" +
                        "Kettlebells are very effective when used for weight loss because they engage the entire body. Plus, they're low impact yet high intensity—ideal for calorie burn. If you're not ready for an overhead swing, stop the bell at shoulder height and let it swing back down between legs.",
                "https://firebasestorage.googleapis.com/v0/b/fitness-activity-432c6.appspot.com/o/Loss%2FKettlebell%20Swing.gif?alt=media&token=8189e17a-9361-44c9-87bb-57317b68a0cb"
            ),
        )
    }

//    fun building(): List<Exercise> {
//        return listOf(
//            Exercise(
//                ""
//            )
//        )
//    }
}