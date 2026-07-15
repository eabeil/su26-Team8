<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal - Dashboard</title>
    <!--tailwind setup-->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 min-h-screen font-sans text-black">

    <!-- SITE HEADER -->
    <header>
        <div class="bg-white border-b border-gray-200">
            <div class="max-w-4xl mx-auto px-6 py-4 flex items-center">
                <h1 class="text-2xl font-extrabold tracking-tight">PetPal</h1>
                <nav class="ml-auto flex items-center gap-6">
                    <a href="/provider/${providerId}/dashboard"
                        class="text-sm font-semibold text-black hover:text-black underline transition-colors">Dashboard</a>
                    <a href="/provider/${providerId}/customer-list"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Customers</a>
                    <a href="/"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
    </header>

    <main class="max-w-4xl mx-auto px-6 py-10 space-y-6">

        <!-- PROFILE HEADER -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <div class="flex flex-col sm:flex-row gap-6">
                <img src="/content/provider-profile.png" alt="Profile picture"
                    class="w-24 h-24 rounded-full object-cover border border-gray-200 flex-shrink-0">
                <div class="flex-1">
                    <h2 class="text-xl font-extrabold tracking-tight mb-2">Sarah Young</h2>
                    <p class="text-sm text-gray-600 mb-6">Dog Walking & Sitting • Greensboro, NC</p>
                    <p class="text-sm text-gray-600 mb-6">myemail@yahoo.com</p>
                    <p class="text-sm text-gray-600 mb-6">(123) 456-7890</p>
                    <p class="text-sm text-gray-500 leading-relaxed">The dog (Canis familiaris or Canis lupus
                        familiaris) is a domesticated descendant of wolves. Also called the domestic dog, it was
                        selectively bred during the Late Pleistocene by hunter-gatherers. Dogs and the modern gray wolf
                        share a common ancestor. Dogs were the first species to be domesticated over 14,000 years
                        ago, before the development of agriculture, though genetic studies suggest the domestication
                        process may have begun over 25,000 years ago. Due to their long association with humans, dogs
                        have gained the ability to thrive on a starch-rich diet that would be inadequate for other
                        canids. <br>
                        Dogs have been bred for desired behaviors, sensory capabilities, and physical attributes. Dog
                        breeds vary widely in shape, size, and color. They have the same number of bones (with the
                        exception of the tail), powerful jaws that house around 42 teeth, and well-developed senses of
                        smell, hearing, and sight. Compared to humans, dogs possess a superior sense of smell and
                        hearing, but inferior visual acuity. Dogs perform many roles for humans, such as hunting,
                        herding, pulling loads, protection, companionship, therapy, aiding disabled people, and
                        assisting police and the military. <br>
                        Communication in dogs includes eye gaze, facial expression, vocalization, body posture
                        (including movements of bodies and limbs), and chemical communication (scents, pheromones, and
                        taste). They mark their territories by urinating on them, which is more likely when entering a
                        new environment. Over the millennia, dogs have uniquely adapted to human behavior; this
                        adaptation includes being able to understand and communicate with humans. As such, the
                        human–canine bond has been a topic of frequent study, and dogs' influence on human society has
                        given them the sobriquet of "man's best friend". <br>
                        The global dog population is estimated at 700 million to 1 billion, distributed around the
                        world. The dog is the most popular pet in the United States, present in 34–40% of households.
                        Developed countries make up approximately 20% of the global dog population, while around 75% of
                        dogs are estimated to be from developing countries, mainly in the form of feral and street dogs.
                    </p>
                    <a href="/provider/${providerId}/profile-edit"
                        class="flex justify-center px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">Edit
                    </a>
                </div>
            </div>
        </section>

        <!-- REVIEWS PREVIEW -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                My ratings
            </h3>
            <p class="text-center text-2xl font-extrabold tracking-tight mb-2">75% like / 25% dislike (4 reviews)</p>
            <a href="/provider/${providerId}/reviews"
                class="flex justify-center px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                View and Respond to Reviews
            </a>
        </section>

        <!-- SERVICES LIST -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                My Services
            </h3>
            <div class="overflow-x-auto">
                <table class="w-full text-sm text-left border-collapse">
                    <thead>
                        <tr class="bg-gray-50 text-xs text-gray-500 uppercase tracking-wider">
                            <th class="px-6 py-4 rounded-lg">Data / Time</th>
                            <th class="px-6 py-4">Service</th>
                            <th class="px-6 py-4">Duration</th>
                            <th class="px-6 py-4 rounded-lg">Price</th>
                        </tr>
                    </thead>
                    <#list jobs as job>
                        <tbody>
                            <tr class="border-b border-gray-200">
                                <td class="px-6 py-4 text-gray-500">${job.time?string("EEE, MMM d '•' h:mm a")}</td>
                                <td class="px-6 py-4 text-gray-500>${job.title}</td>
                                <td class="px-6 py-4 text-gray-500">${job.duration}</td>
                                <td class="px-6 py-4 text-gray-500">${job.price}</td>
                            </tr>
                        </tbody>
                    </#list>
                </table>
                <a href="/provider/${providerId}/edit-services"
                    class="flex justify-center px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">Edit
                </a>
            </div>
        </section>

        <!-- UPDATES -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                My Updates
            </h3>
            <div class="space-y-6">

                <#list updates as update>
                <article class="border border-gray-200 rounded-lg p-8">
                    <img src="${update.imageUrl}" alt="Update picture" class="w-full h-auto object-cover">
                    <div class="mt-5">
                        <p class="text-sm text-gray-500">
                            ${update.description}
                        </p>
                        <p class="text-xs text-gray-500 mt-1">
                            ${update.createdAt?string("EEE, MMM d '•' h:mm a")}
                        </p>
                    </div>
                    <div class="flex items-center gap-6 mt-3">
                        <a href="#"
                            class="ml-auto px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">Delete
                        </a>
                    </div>
                </article>
                </#list>

            </div>
        </section>
    </main>
</body>

</html>