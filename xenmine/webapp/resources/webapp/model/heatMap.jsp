


<!DOCTYPE html>
<html lang="en" class="">
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# object: http://ogp.me/ns/object# article: http://ogp.me/ns/article# profile: http://ogp.me/ns/profile#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    
    
    <title>intermine/heatMap.jsp at modmine-33 · intermine/intermine · GitHub</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub">
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub">
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png">
    <meta property="fb:app_id" content="1401488693436528">

      <meta content="@github" name="twitter:site" /><meta content="summary" name="twitter:card" /><meta content="intermine/intermine" name="twitter:title" /><meta content="intermine - A powerful open source data warehouse system" name="twitter:description" /><meta content="https://avatars3.githubusercontent.com/u/179399?v=3&amp;s=400" name="twitter:image:src" />
      <meta content="GitHub" property="og:site_name" /><meta content="object" property="og:type" /><meta content="https://avatars3.githubusercontent.com/u/179399?v=3&amp;s=400" property="og:image" /><meta content="intermine/intermine" property="og:title" /><meta content="https://github.com/intermine/intermine" property="og:url" /><meta content="intermine - A powerful open source data warehouse system" property="og:description" />
      <meta name="browser-stats-url" content="https://api.github.com/_private/browser/stats">
    <meta name="browser-errors-url" content="https://api.github.com/_private/browser/errors">
    <link rel="assets" href="https://assets-cdn.github.com/">
    
    <meta name="pjax-timeout" content="1000">
    

    <meta name="msapplication-TileImage" content="/windows-tile.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="selected-link" value="repo_source" data-pjax-transient>
      <meta name="google-analytics" content="UA-3769691-2">

    <meta content="collector.githubapp.com" name="octolytics-host" /><meta content="collector-cdn.github.com" name="octolytics-script-host" /><meta content="github" name="octolytics-app-id" /><meta content="ADE41338:4D89:4B78DBA:552ACD35" name="octolytics-dimension-request_id" />
    
    <meta content="Rails, view, blob#show" name="analytics-event" />
    <meta class="js-ga-set" name="dimension1" content="Logged Out">
    <meta class="js-ga-set" name="dimension2" content="Header v3">
    <meta name="is-dotcom" content="true">
    <meta name="hostname" content="github.com">
    <meta name="user-login" content="">

    
    <link rel="icon" type="image/x-icon" href="https://assets-cdn.github.com/favicon.ico">


    <meta content="authenticity_token" name="csrf-param" />
<meta content="xTLQCSu8gu04XTGLm53bGOuty3zGnE42xQjig3UMAzVLnoz0hjDc1/h8KTLCQNKxs0m7NM0jKCIKfY+NFLU9rA==" name="csrf-token" />

    <link href="https://assets-cdn.github.com/assets/github-6a4174aa7e25ce4268078491fdfa22273eef841d99e1ab07ccce9b89c747f55d.css" media="all" rel="stylesheet" />
    <link href="https://assets-cdn.github.com/assets/github2-5b5e999e041f2586f9dc06b940229076225e6ec5ab5406be292a87331325fdc0.css" media="all" rel="stylesheet" />
    
    


    <meta http-equiv="x-pjax-version" content="f227b009e29272e5bb7d2b5ef04d2783">

      
  <meta name="description" content="intermine - A powerful open source data warehouse system">
  <meta name="go-import" content="github.com/intermine/intermine git https://github.com/intermine/intermine.git">

  <meta content="179399" name="octolytics-dimension-user_id" /><meta content="intermine" name="octolytics-dimension-user_login" /><meta content="4858466" name="octolytics-dimension-repository_id" /><meta content="intermine/intermine" name="octolytics-dimension-repository_nwo" /><meta content="true" name="octolytics-dimension-repository_public" /><meta content="false" name="octolytics-dimension-repository_is_fork" /><meta content="4858466" name="octolytics-dimension-repository_network_root_id" /><meta content="intermine/intermine" name="octolytics-dimension-repository_network_root_nwo" />
  <link href="https://github.com/intermine/intermine/commits/modmine-33.atom" rel="alternate" title="Recent Commits to intermine:modmine-33" type="application/atom+xml">

  </head>


  <body class="logged_out  env-production  vis-public page-blob">
    <a href="#start-of-content" tabindex="1" class="accessibility-aid js-skip-to-content">Skip to content</a>
    <div class="wrapper">
      
      
      


        
        <div class="header header-logged-out" role="banner">
  <div class="container clearfix">

    <a class="header-logo-wordmark" href="https://github.com/" data-ga-click="(Logged out) Header, go to homepage, icon:logo-wordmark">
      <span class="mega-octicon octicon-logo-github"></span>
    </a>

    <div class="header-actions" role="navigation">
        <a class="btn btn-primary" href="/join" data-ga-click="(Logged out) Header, clicked Sign up, text:sign-up">Sign up</a>
      <a class="btn" href="/login?return_to=%2Fintermine%2Fintermine%2Fblob%2Fmodmine-33%2Fmodmine%2Fwebapp%2Fresources%2Fwebapp%2Fmodel%2FheatMap.jsp" data-ga-click="(Logged out) Header, clicked Sign in, text:sign-in">Sign in</a>
    </div>

    <div class="site-search repo-scope js-site-search" role="search">
      <form accept-charset="UTF-8" action="/intermine/intermine/search" class="js-site-search-form" data-global-search-url="/search" data-repo-search-url="/intermine/intermine/search" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
  <input type="text"
    class="js-site-search-field is-clearable"
    data-hotkey="s"
    name="q"
    placeholder="Search"
    data-global-scope-placeholder="Search GitHub"
    data-repo-scope-placeholder="Search"
    tabindex="1"
    autocapitalize="off">
  <div class="scope-badge">This repository</div>
</form>
    </div>

      <ul class="header-nav left" role="navigation">
          <li class="header-nav-item">
            <a class="header-nav-link" href="/explore" data-ga-click="(Logged out) Header, go to explore, text:explore">Explore</a>
          </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="/features" data-ga-click="(Logged out) Header, go to features, text:features">Features</a>
          </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="https://enterprise.github.com/" data-ga-click="(Logged out) Header, go to enterprise, text:enterprise">Enterprise</a>
          </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="/blog" data-ga-click="(Logged out) Header, go to blog, text:blog">Blog</a>
          </li>
      </ul>

  </div>
</div>



      <div id="start-of-content" class="accessibility-aid"></div>
          <div class="site" itemscope itemtype="http://schema.org/WebPage">
    <div id="js-flash-container">
      
    </div>
    <div class="pagehead repohead instapaper_ignore readability-menu">
      <div class="container">
        
<ul class="pagehead-actions">

  <li>
      <a href="/login?return_to=%2Fintermine%2Fintermine"
    class="btn btn-sm btn-with-count tooltipped tooltipped-n"
    aria-label="You must be signed in to watch a repository" rel="nofollow">
    <span class="octicon octicon-eye"></span>
    Watch
  </a>
  <a class="social-count" href="/intermine/intermine/watchers">
    7
  </a>

  </li>

  <li>
      <a href="/login?return_to=%2Fintermine%2Fintermine"
    class="btn btn-sm btn-with-count tooltipped tooltipped-n"
    aria-label="You must be signed in to star a repository" rel="nofollow">
    <span class="octicon octicon-star"></span>
    Star
  </a>

    <a class="social-count js-social-count" href="/intermine/intermine/stargazers">
      46
    </a>

  </li>

    <li>
      <a href="/login?return_to=%2Fintermine%2Fintermine"
        class="btn btn-sm btn-with-count tooltipped tooltipped-n"
        aria-label="You must be signed in to fork a repository" rel="nofollow">
        <span class="octicon octicon-repo-forked"></span>
        Fork
      </a>
      <a href="/intermine/intermine/network" class="social-count">
        192
      </a>
    </li>
</ul>

        <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public">
          <span class="mega-octicon octicon-repo"></span>
          <span class="author"><a href="/intermine" class="url fn" itemprop="url" rel="author"><span itemprop="title">intermine</span></a></span><!--
       --><span class="path-divider">/</span><!--
       --><strong><a href="/intermine/intermine" class="js-current-repository" data-pjax="#js-repo-pjax-container">intermine</a></strong>

          <span class="page-context-loader">
            <img alt="" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
          </span>

        </h1>
      </div><!-- /.container -->
    </div><!-- /.repohead -->

    <div class="container">
      <div class="repository-with-sidebar repo-container new-discussion-timeline  ">
        <div class="repository-sidebar clearfix">
            
<nav class="sunken-menu repo-nav js-repo-nav js-sidenav-container-pjax js-octicon-loaders"
     role="navigation"
     data-pjax="#js-repo-pjax-container"
     data-issue-count-url="/intermine/intermine/issues/counts">
  <ul class="sunken-menu-group">
    <li class="tooltipped tooltipped-w" aria-label="Code">
      <a href="/intermine/intermine/tree/modmine-33" aria-label="Code" class="selected js-selected-navigation-item sunken-menu-item" data-hotkey="g c" data-selected-links="repo_source repo_downloads repo_commits repo_releases repo_tags repo_branches /intermine/intermine/tree/modmine-33">
        <span class="octicon octicon-code"></span> <span class="full-word">Code</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>

      <li class="tooltipped tooltipped-w" aria-label="Issues">
        <a href="/intermine/intermine/issues" aria-label="Issues" class="js-selected-navigation-item sunken-menu-item" data-hotkey="g i" data-selected-links="repo_issues repo_labels repo_milestones /intermine/intermine/issues">
          <span class="octicon octicon-issue-opened"></span> <span class="full-word">Issues</span>
          <span class="js-issue-replace-counter"></span>
          <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>      </li>

    <li class="tooltipped tooltipped-w" aria-label="Pull requests">
      <a href="/intermine/intermine/pulls" aria-label="Pull requests" class="js-selected-navigation-item sunken-menu-item" data-hotkey="g p" data-selected-links="repo_pulls /intermine/intermine/pulls">
          <span class="octicon octicon-git-pull-request"></span> <span class="full-word">Pull requests</span>
          <span class="js-pull-replace-counter"></span>
          <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>

  </ul>
  <div class="sunken-menu-separator"></div>
  <ul class="sunken-menu-group">

    <li class="tooltipped tooltipped-w" aria-label="Pulse">
      <a href="/intermine/intermine/pulse" aria-label="Pulse" class="js-selected-navigation-item sunken-menu-item" data-selected-links="pulse /intermine/intermine/pulse">
        <span class="octicon octicon-pulse"></span> <span class="full-word">Pulse</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>

    <li class="tooltipped tooltipped-w" aria-label="Graphs">
      <a href="/intermine/intermine/graphs" aria-label="Graphs" class="js-selected-navigation-item sunken-menu-item" data-selected-links="repo_graphs repo_contributors /intermine/intermine/graphs">
        <span class="octicon octicon-graph"></span> <span class="full-word">Graphs</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>
  </ul>


</nav>

              <div class="only-with-full-nav">
                  
<div class="clone-url open"
  data-protocol-type="http"
  data-url="/users/set_protocol?protocol_selector=http&amp;protocol_type=clone">
  <h3><span class="text-emphasized">HTTPS</span> clone URL</h3>
  <div class="input-group js-zeroclipboard-container">
    <input type="text" class="input-mini input-monospace js-url-field js-zeroclipboard-target"
           value="https://github.com/intermine/intermine.git" readonly="readonly">
    <span class="input-group-button">
      <button aria-label="Copy to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" data-copy-hint="Copy to clipboard" type="button"><span class="octicon octicon-clippy"></span></button>
    </span>
  </div>
</div>

  
<div class="clone-url "
  data-protocol-type="subversion"
  data-url="/users/set_protocol?protocol_selector=subversion&amp;protocol_type=clone">
  <h3><span class="text-emphasized">Subversion</span> checkout URL</h3>
  <div class="input-group js-zeroclipboard-container">
    <input type="text" class="input-mini input-monospace js-url-field js-zeroclipboard-target"
           value="https://github.com/intermine/intermine" readonly="readonly">
    <span class="input-group-button">
      <button aria-label="Copy to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" data-copy-hint="Copy to clipboard" type="button"><span class="octicon octicon-clippy"></span></button>
    </span>
  </div>
</div>



<p class="clone-options">You can clone with
  <a href="#" class="js-clone-selector" data-protocol="http">HTTPS</a> or <a href="#" class="js-clone-selector" data-protocol="subversion">Subversion</a>.
  <a href="https://help.github.com/articles/which-remote-url-should-i-use" class="help tooltipped tooltipped-n" aria-label="Get help on which URL is right for you.">
    <span class="octicon octicon-question"></span>
  </a>
</p>




                <a href="/intermine/intermine/archive/modmine-33.zip"
                   class="btn btn-sm sidebar-button"
                   aria-label="Download the contents of intermine/intermine as a zip file"
                   title="Download the contents of intermine/intermine as a zip file"
                   rel="nofollow">
                  <span class="octicon octicon-cloud-download"></span>
                  Download ZIP
                </a>
              </div>
        </div><!-- /.repository-sidebar -->

        <div id="js-repo-pjax-container" class="repository-content context-loader-container" data-pjax-container>
          

<a href="/intermine/intermine/blob/3363998dbed6555f672243a47d2e844fc81bd0fe/modmine/webapp/resources/webapp/model/heatMap.jsp" class="hidden js-permalink-shortcut" data-hotkey="y">Permalink</a>

<!-- blob contrib key: blob_contributors:v21:3c533aa93623764aaa74eaa9e5b931bb -->

<div class="file-navigation js-zeroclipboard-container">
  
<div class="select-menu js-menu-container js-select-menu left">
  <span class="btn btn-sm select-menu-button js-menu-target css-truncate" data-hotkey="w"
    data-master-branch="beta"
    data-ref="modmine-33"
    title="modmine-33"
    role="button" aria-label="Switch branches or tags" tabindex="0" aria-haspopup="true">
    <span class="octicon octicon-tag"></span>
    <i>tag:</i>
    <span class="js-select-button css-truncate-target">modmine-33</span>
  </span>

  <div class="select-menu-modal-holder js-menu-content js-navigation-container" data-pjax aria-hidden="true">

    <div class="select-menu-modal">
      <div class="select-menu-header">
        <span class="select-menu-title">Switch branches/tags</span>
        <span class="octicon octicon-x js-menu-close" role="button" aria-label="Close"></span>
      </div>

      <div class="select-menu-filters">
        <div class="select-menu-text-filter">
          <input type="text" aria-label="Filter branches/tags" id="context-commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Filter branches/tags">
        </div>
        <div class="select-menu-tabs">
          <ul>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="branches" data-filter-placeholder="Filter branches/tags" class="js-select-menu-tab">Branches</a>
            </li>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="tags" data-filter-placeholder="Find a tag…" class="js-select-menu-tab">Tags</a>
            </li>
          </ul>
        </div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="branches">

        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/beta/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="beta"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="beta">
                beta
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/corporate-jbrowse/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="corporate-jbrowse"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="corporate-jbrowse">
                corporate-jbrowse
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/cytoscape/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="cytoscape"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="cytoscape">
                cytoscape
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/dev/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="dev"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="dev">
                dev
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/esyn/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="esyn"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="esyn">
                esyn
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/flat-reactome/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="flat-reactome"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="flat-reactome">
                flat-reactome
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/gh-pages/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="gh-pages"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="gh-pages">
                gh-pages
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/gradle/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="gradle"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="gradle">
                gradle
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/hotfix-i957/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="hotfix-i957"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="hotfix-i957">
                hotfix-i957
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/human-identifiers/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="human-identifiers"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="human-identifiers">
                human-identifiers
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/i169/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="i169"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="i169">
                i169
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/i797/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="i797"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="i797">
                i797
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/jbrowse/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="jbrowse"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="jbrowse">
                jbrowse
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/like/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="like"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="like">
                like
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/master/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="master"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="master">
                master
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/oauth-2-support/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="oauth-2-support"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="oauth-2-support">
                oauth-2-support
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/paxtools/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="paxtools"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="paxtools">
                paxtools
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/protein-interactions/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="protein-interactions"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="protein-interactions">
                protein-interactions
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/selenium/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="selenium"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="selenium">
                selenium
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/simple-items/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="simple-items"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="simple-items">
                simple-items
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/intermine/intermine/blob/testmodel-with-dates/modmine/webapp/resources/webapp/model/heatMap.jsp"
               data-name="testmodel-with-dates"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="testmodel-with-dates">
                testmodel-with-dates
              </span>
            </a>
        </div>

          <div class="select-menu-no-results">Nothing to show</div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="tags">
        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <div class="select-menu-item js-navigation-item selected">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/modmine-33/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="modmine-33"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="modmine-33">modmine-33</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/modmine-32/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="modmine-32"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="modmine-32">modmine-32</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/modmine-31/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="modmine-31"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="modmine-31">modmine-31</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/modmine-30beta/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="modmine-30beta"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="modmine-30beta">modmine-30beta</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/metabolicmine-aug2012-oct_update/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="metabolicmine-aug2012-oct_update"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="metabolicmine-aug2012-oct_update">metabolicmine-aug2012-oct_update</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/metabolicmine-aug2012/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="metabolicmine-aug2012"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="metabolicmine-aug2012">metabolicmine-aug2012</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.4.2/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.4.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.4.2">intermine-1.4.2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.4.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.4.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.4.1">intermine-1.4.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.4/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.4">intermine-1.4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.10/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.10"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.10">intermine-1.3.10</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.9/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.9"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.9">intermine-1.3.9</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.8/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.8"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.8">intermine-1.3.8</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.7/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.7"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.7">intermine-1.3.7</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.6/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.6"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.6">intermine-1.3.6</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.5/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.5"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.5">intermine-1.3.5</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.4/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.4"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.4">intermine-1.3.4</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.3/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.3">intermine-1.3.3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.2/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.2">intermine-1.3.2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3.1">intermine-1.3.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.3/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.3">intermine-1.3</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.2.2/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.2.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.2.2">intermine-1.2.2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.2.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.2.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.2.1">intermine-1.2.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.2/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.2">intermine-1.2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.1.2/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.1.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.1.2">intermine-1.1.2</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.1.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.1.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.1.1">intermine-1.1.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.1">intermine-1.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.0.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.0.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.0.1">intermine-1.0.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-1.0/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-1.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-1.0">intermine-1.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/intermine-0.99/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="intermine-0.99"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="intermine-0.99">intermine-0.99</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-40.0/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-40.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-40.0">flymine-40.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-39.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-39.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-39.1">flymine-39.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-39.0/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-39.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-39.0">flymine-39.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-38.0/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-38.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-38.0">flymine-38.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-37.0/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-37.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-37.0">flymine-37.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-36.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-36.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-36.1">flymine-36.1</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-35.0/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-35.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-35.0">flymine-35.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-34.0/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-34.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-34.0">flymine-34.0</a>
            </div>
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/intermine/intermine/tree/flymine-33.1/modmine/webapp/resources/webapp/model/heatMap.jsp"
                 data-name="flymine-33.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text css-truncate-target"
                 title="flymine-33.1">flymine-33.1</a>
            </div>
        </div>

        <div class="select-menu-no-results">Nothing to show</div>
      </div>

    </div>
  </div>
</div>

  <div class="btn-group right">
    <a href="/intermine/intermine/find/modmine-33"
          class="js-show-file-finder btn btn-sm empty-icon tooltipped tooltipped-s"
          data-pjax
          data-hotkey="t"
          aria-label="Quickly jump between files">
      <span class="octicon octicon-list-unordered"></span>
    </a>
    <button aria-label="Copy file path to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" data-copy-hint="Copy file path to clipboard" type="button"><span class="octicon octicon-clippy"></span></button>
  </div>

  <div class="breadcrumb js-zeroclipboard-target">
    <span class='repo-root js-repo-root'><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/intermine/intermine/tree/modmine-33" class="" data-branch="modmine-33" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">intermine</span></a></span></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/intermine/intermine/tree/modmine-33/modmine" class="" data-branch="modmine-33" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">modmine</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/intermine/intermine/tree/modmine-33/modmine/webapp" class="" data-branch="modmine-33" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">webapp</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/intermine/intermine/tree/modmine-33/modmine/webapp/resources" class="" data-branch="modmine-33" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">resources</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/intermine/intermine/tree/modmine-33/modmine/webapp/resources/webapp" class="" data-branch="modmine-33" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">webapp</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/intermine/intermine/tree/modmine-33/modmine/webapp/resources/webapp/model" class="" data-branch="modmine-33" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">model</span></a></span><span class="separator">/</span><strong class="final-path">heatMap.jsp</strong>
  </div>
</div>


  <div class="commit file-history-tease">
    <div class="file-history-tease-header">
        <img alt="@danielabutano" class="avatar" data-user="1678179" height="24" src="https://avatars1.githubusercontent.com/u/1678179?v=3&amp;s=48" width="24" />
        <span class="author"><a href="/danielabutano" rel="contributor">danielabutano</a></span>
        <time datetime="2012-04-24T08:46:58Z" is="relative-time">Apr 24, 2012</time>
        <div class="commit-title">
            <a href="/intermine/intermine/commit/9511eebed8b5088bcbb468f25191e77eb5bf4493" class="message" data-pjax="true" title="Replaced method to enable/disable field with jquery">Replaced method to enable/disable field with jquery</a>
        </div>
    </div>

    <div class="participation">
      <p class="quickstat">
        <a href="#blob_contributors_box" rel="facebox">
          <strong>2</strong>
           contributors
        </a>
      </p>
          <a class="avatar-link tooltipped tooltipped-s" aria-label="boboppie" href="/intermine/intermine/commits/modmine-33/modmine/webapp/resources/webapp/model/heatMap.jsp?author=boboppie"><img alt="@boboppie" class="avatar" data-user="543789" height="20" src="https://avatars2.githubusercontent.com/u/543789?v=3&amp;s=40" width="20" /> </a>
    <a class="avatar-link tooltipped tooltipped-s" aria-label="danielabutano" href="/intermine/intermine/commits/modmine-33/modmine/webapp/resources/webapp/model/heatMap.jsp?author=danielabutano"><img alt="@danielabutano" class="avatar" data-user="1678179" height="20" src="https://avatars3.githubusercontent.com/u/1678179?v=3&amp;s=40" width="20" /> </a>


    </div>
    <div id="blob_contributors_box" style="display:none">
      <h2 class="facebox-header">Users who have contributed to this file</h2>
      <ul class="facebox-user-list">
          <li class="facebox-user-list-item">
            <img alt="@boboppie" data-user="543789" height="24" src="https://avatars0.githubusercontent.com/u/543789?v=3&amp;s=48" width="24" />
            <a href="/boboppie">boboppie</a>
          </li>
          <li class="facebox-user-list-item">
            <img alt="@danielabutano" data-user="1678179" height="24" src="https://avatars1.githubusercontent.com/u/1678179?v=3&amp;s=48" width="24" />
            <a href="/danielabutano">danielabutano</a>
          </li>
      </ul>
    </div>
  </div>

<div class="file">
  <div class="file-header">
    <div class="file-actions">

      <div class="btn-group">
        <a href="/intermine/intermine/raw/modmine-33/modmine/webapp/resources/webapp/model/heatMap.jsp" class="btn btn-sm " id="raw-url">Raw</a>
          <a href="/intermine/intermine/blame/modmine-33/modmine/webapp/resources/webapp/model/heatMap.jsp" class="btn btn-sm js-update-url-with-hash">Blame</a>
        <a href="/intermine/intermine/commits/modmine-33/modmine/webapp/resources/webapp/model/heatMap.jsp" class="btn btn-sm " rel="nofollow">History</a>
      </div>


          <button type="button" class="octicon-btn disabled tooltipped tooltipped-n" aria-label="You must be signed in to make or propose changes">
            <span class="octicon octicon-pencil"></span>
          </button>

        <button type="button" class="octicon-btn octicon-btn-danger disabled tooltipped tooltipped-n" aria-label="You must be signed in to make or propose changes">
          <span class="octicon octicon-trashcan"></span>
        </button>
    </div>

    <div class="file-info">
        336 lines (288 sloc)
        <span class="file-info-divider"></span>
      18.011 kb
    </div>
  </div>
  
  <div class="blob-wrapper data type-java-server-pages">
      <table class="highlight tab-size-8 js-file-line-container">
      <tr>
        <td id="L1" class="blob-num js-line-number" data-line-number="1"></td>
        <td id="LC1" class="blob-code js-file-line">&lt;%@ <span class="pl-k">taglib</span> <span class="pl-e">uri</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>/WEB-INF/struts-html.tld<span class="pl-pds">&quot;</span></span> <span class="pl-e">prefix</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>html<span class="pl-pds">&quot;</span></span> %&gt;</td>
      </tr>
      <tr>
        <td id="L2" class="blob-num js-line-number" data-line-number="2"></td>
        <td id="LC2" class="blob-code js-file-line">&lt;%@ <span class="pl-k">taglib</span> <span class="pl-e">uri</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>http://java.sun.com/jsp/jstl/core<span class="pl-pds">&quot;</span></span> <span class="pl-e">prefix</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>c<span class="pl-pds">&quot;</span></span> %&gt;</td>
      </tr>
      <tr>
        <td id="L3" class="blob-num js-line-number" data-line-number="3"></td>
        <td id="LC3" class="blob-code js-file-line">&lt;%@ <span class="pl-k">taglib</span> <span class="pl-e">uri</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>http://java.sun.com/jsp/jstl/functions<span class="pl-pds">&quot;</span></span> <span class="pl-e">prefix</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>fn<span class="pl-pds">&quot;</span></span>%&gt;</td>
      </tr>
      <tr>
        <td id="L4" class="blob-num js-line-number" data-line-number="4"></td>
        <td id="LC4" class="blob-code js-file-line">&lt;%@ <span class="pl-k">page</span> <span class="pl-e">import</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>java.net.URLEncoder<span class="pl-pds">&quot;</span></span> <span class="pl-e">language</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>java<span class="pl-pds">&quot;</span></span> %&gt;</td>
      </tr>
      <tr>
        <td id="L5" class="blob-num js-line-number" data-line-number="5"></td>
        <td id="LC5" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L6" class="blob-num js-line-number" data-line-number="6"></td>
        <td id="LC6" class="blob-code js-file-line"><span class="pl-c">&lt;!-- heatMap.jsp --&gt;</span></td>
      </tr>
      <tr>
        <td id="L7" class="blob-num js-line-number" data-line-number="7"></td>
        <td id="LC7" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L8" class="blob-num js-line-number" data-line-number="8"></td>
        <td id="LC8" class="blob-code js-file-line">&lt;<span class="pl-ent">html</span><span class="pl-e">:xhtml</span> /&gt;</td>
      </tr>
      <tr>
        <td id="L9" class="blob-num js-line-number" data-line-number="9"></td>
        <td id="LC9" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L10" class="blob-num js-line-number" data-line-number="10"></td>
        <td id="LC10" class="blob-code js-file-line">&lt;<span class="pl-ent">tiles:importAttribute</span> /&gt;</td>
      </tr>
      <tr>
        <td id="L11" class="blob-num js-line-number" data-line-number="11"></td>
        <td id="LC11" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L12" class="blob-num js-line-number" data-line-number="12"></td>
        <td id="LC12" class="blob-code js-file-line"><span class="pl-c">&lt;!--[if IE]&gt;&lt;script type=&quot;text/javascript&quot; src=&quot;model/canvasXpress/js/excanvas.js&quot;&gt;&lt;/script&gt;&lt;![endif]--&gt;</span></td>
      </tr>
      <tr>
        <td id="L13" class="blob-num js-line-number" data-line-number="13"></td>
        <td id="LC13" class="blob-code js-file-line"><span class="pl-s1">    &lt;<span class="pl-ent">script</span> <span class="pl-e">type</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>text/javascript<span class="pl-pds">&quot;</span></span> <span class="pl-e">src</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>model/canvasXpress/js/canvasXpress.min.js<span class="pl-pds">&quot;</span></span>&gt;&lt;/<span class="pl-ent">script</span>&gt;</span></td>
      </tr>
      <tr>
        <td id="L14" class="blob-num js-line-number" data-line-number="14"></td>
        <td id="LC14" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L15" class="blob-num js-line-number" data-line-number="15"></td>
        <td id="LC15" class="blob-code js-file-line">&lt;<span class="pl-ent">div</span> <span class="pl-e">class</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>body<span class="pl-pds">&quot;</span></span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>expression_div<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L16" class="blob-num js-line-number" data-line-number="16"></td>
        <td id="LC16" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L17" class="blob-num js-line-number" data-line-number="17"></td>
        <td id="LC17" class="blob-code js-file-line"><span class="pl-s1">&lt;<span class="pl-ent">script</span> <span class="pl-e">type</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>text/javascript<span class="pl-pds">&quot;</span></span> <span class="pl-e">charset</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>utf-8<span class="pl-pds">&quot;</span></span>&gt;</span></td>
      </tr>
      <tr>
        <td id="L18" class="blob-num js-line-number" data-line-number="18"></td>
        <td id="LC18" class="blob-code js-file-line"><span class="pl-s1">jQuery(<span class="pl-c1">document</span>).ready(<span class="pl-k">function</span> () {</span></td>
      </tr>
      <tr>
        <td id="L19" class="blob-num js-line-number" data-line-number="19"></td>
        <td id="LC19" class="blob-code js-file-line"><span class="pl-s1">    <span class="pl-k">var</span> feature_count <span class="pl-k">=</span> <span class="pl-c1">parseInt</span>(<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">FeatureCount</span></span><span class="pl-pse"><span class="pl-s1">}</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L20" class="blob-num js-line-number" data-line-number="20"></td>
        <td id="LC20" class="blob-code js-file-line"><span class="pl-s1">    <span class="pl-k">if</span> (feature_count <span class="pl-k">&gt;</span> <span class="pl-c1">100</span>) {</span></td>
      </tr>
      <tr>
        <td id="L21" class="blob-num js-line-number" data-line-number="21"></td>
        <td id="LC21" class="blob-code js-file-line"><span class="pl-s1">        jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#heatmapGraph<span class="pl-pds">&quot;</span></span>).hide();</span></td>
      </tr>
      <tr>
        <td id="L22" class="blob-num js-line-number" data-line-number="22"></td>
        <td id="LC22" class="blob-code js-file-line"><span class="pl-s1">    } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L23" class="blob-num js-line-number" data-line-number="23"></td>
        <td id="LC23" class="blob-code js-file-line"><span class="pl-s1">        jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#heatmapGraph<span class="pl-pds">&quot;</span></span>).show();</span></td>
      </tr>
      <tr>
        <td id="L24" class="blob-num js-line-number" data-line-number="24"></td>
        <td id="LC24" class="blob-code js-file-line"><span class="pl-s1">    }</span></td>
      </tr>
      <tr>
        <td id="L25" class="blob-num js-line-number" data-line-number="25"></td>
        <td id="LC25" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L26" class="blob-num js-line-number" data-line-number="26"></td>
        <td id="LC26" class="blob-code js-file-line"><span class="pl-s1">    jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#bro<span class="pl-pds">&quot;</span></span>).<span class="pl-c1">click</span>(<span class="pl-k">function</span> () {</span></td>
      </tr>
      <tr>
        <td id="L27" class="blob-num js-line-number" data-line-number="27"></td>
        <td id="LC27" class="blob-code js-file-line"><span class="pl-s1">       <span class="pl-k">if</span>(jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#heatmapGraph<span class="pl-pds">&quot;</span></span>).is(<span class="pl-s"><span class="pl-pds">&quot;</span>:hidden<span class="pl-pds">&quot;</span></span>)) {</span></td>
      </tr>
      <tr>
        <td id="L28" class="blob-num js-line-number" data-line-number="28"></td>
        <td id="LC28" class="blob-code js-file-line"><span class="pl-s1">         jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#oc<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&quot;</span>src<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>images/disclosed.gif<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L29" class="blob-num js-line-number" data-line-number="29"></td>
        <td id="LC29" class="blob-code js-file-line"><span class="pl-s1">       } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L30" class="blob-num js-line-number" data-line-number="30"></td>
        <td id="LC30" class="blob-code js-file-line"><span class="pl-s1">         jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#oc<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&quot;</span>src<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>images/undisclosed.gif<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L31" class="blob-num js-line-number" data-line-number="31"></td>
        <td id="LC31" class="blob-code js-file-line"><span class="pl-s1">       }</span></td>
      </tr>
      <tr>
        <td id="L32" class="blob-num js-line-number" data-line-number="32"></td>
        <td id="LC32" class="blob-code js-file-line"><span class="pl-s1">       jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#heatmapGraph<span class="pl-pds">&quot;</span></span>).toggle(<span class="pl-s"><span class="pl-pds">&quot;</span>slow<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L33" class="blob-num js-line-number" data-line-number="33"></td>
        <td id="LC33" class="blob-code js-file-line"><span class="pl-s1">    });</span></td>
      </tr>
      <tr>
        <td id="L34" class="blob-num js-line-number" data-line-number="34"></td>
        <td id="LC34" class="blob-code js-file-line"><span class="pl-s1">})</span></td>
      </tr>
      <tr>
        <td id="L35" class="blob-num js-line-number" data-line-number="35"></td>
        <td id="LC35" class="blob-code js-file-line"><span class="pl-s1">&lt;/<span class="pl-ent">script</span>&gt;</span></td>
      </tr>
      <tr>
        <td id="L36" class="blob-num js-line-number" data-line-number="36"></td>
        <td id="LC36" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L37" class="blob-num js-line-number" data-line-number="37"></td>
        <td id="LC37" class="blob-code js-file-line">&lt;<span class="pl-ent">c:set</span> <span class="pl-e">var</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>MAX_CLUSTER<span class="pl-pds">&quot;</span></span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>250<span class="pl-pds">&quot;</span></span> /&gt;</td>
      </tr>
      <tr>
        <td id="L38" class="blob-num js-line-number" data-line-number="38"></td>
        <td id="LC38" class="blob-code js-file-line">&lt;<span class="pl-ent">c:set</span> <span class="pl-e">var</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>MAX_MAP<span class="pl-pds">&quot;</span></span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>600<span class="pl-pds">&quot;</span></span> /&gt;</td>
      </tr>
      <tr>
        <td id="L39" class="blob-num js-line-number" data-line-number="39"></td>
        <td id="LC39" class="blob-code js-file-line">&lt;<span class="pl-ent">c:set</span> <span class="pl-e">var</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>MAX_DEFAULT_OPEN<span class="pl-pds">&quot;</span></span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>100<span class="pl-pds">&quot;</span></span> /&gt;</td>
      </tr>
      <tr>
        <td id="L40" class="blob-num js-line-number" data-line-number="40"></td>
        <td id="LC40" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L41" class="blob-num js-line-number" data-line-number="41"></td>
        <td id="LC41" class="blob-code js-file-line"><span class="pl-c">&lt;%--</span></td>
      </tr>
      <tr>
        <td id="L42" class="blob-num js-line-number" data-line-number="42"></td>
        <td id="LC42" class="blob-code js-file-line"><span class="pl-c">&lt;hr&gt;</span></td>
      </tr>
      <tr>
        <td id="L43" class="blob-num js-line-number" data-line-number="43"></td>
        <td id="LC43" class="blob-code js-file-line"><span class="pl-c">${expressionScoreJSONCellLine}</span></td>
      </tr>
      <tr>
        <td id="L44" class="blob-num js-line-number" data-line-number="44"></td>
        <td id="LC44" class="blob-code js-file-line"><span class="pl-c">&lt;hr&gt;</span></td>
      </tr>
      <tr>
        <td id="L45" class="blob-num js-line-number" data-line-number="45"></td>
        <td id="LC45" class="blob-code js-file-line"><span class="pl-c">--%&gt;</span></td>
      </tr>
      <tr>
        <td id="L46" class="blob-num js-line-number" data-line-number="46"></td>
        <td id="LC46" class="blob-code js-file-line">    &lt;<span class="pl-ent">div</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>heatmap_div<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L47" class="blob-num js-line-number" data-line-number="47"></td>
        <td id="LC47" class="blob-code js-file-line">        &lt;<span class="pl-ent">p</span>&gt;</td>
      </tr>
      <tr>
        <td id="L48" class="blob-num js-line-number" data-line-number="48"></td>
        <td id="LC48" class="blob-code js-file-line">          &lt;<span class="pl-ent">h2</span>&gt;</td>
      </tr>
      <tr>
        <td id="L49" class="blob-num js-line-number" data-line-number="49"></td>
        <td id="LC49" class="blob-code js-file-line">              &lt;<span class="pl-ent">c:choose</span>&gt;</td>
      </tr>
      <tr>
        <td id="L50" class="blob-num js-line-number" data-line-number="50"></td>
        <td id="LC50" class="blob-code js-file-line">                &lt;<span class="pl-ent">c:when</span> <span class="pl-e">test</span>=<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&#39;</span>gene<span class="pl-pds">&#39;</span></span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L51" class="blob-num js-line-number" data-line-number="51"></td>
        <td id="LC51" class="blob-code js-file-line">                  <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>heatmap.geneExpressionScoreTitle<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span></td>
      </tr>
      <tr>
        <td id="L52" class="blob-num js-line-number" data-line-number="52"></td>
        <td id="LC52" class="blob-code js-file-line">                &lt;/<span class="pl-ent">c:when</span>&gt;</td>
      </tr>
      <tr>
        <td id="L53" class="blob-num js-line-number" data-line-number="53"></td>
        <td id="LC53" class="blob-code js-file-line">                &lt;<span class="pl-ent">c:when</span> <span class="pl-e">test</span>=<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&#39;</span>exon<span class="pl-pds">&#39;</span></span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L54" class="blob-num js-line-number" data-line-number="54"></td>
        <td id="LC54" class="blob-code js-file-line">                  <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>heatmap.exonExpressionScoreTitle<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span></td>
      </tr>
      <tr>
        <td id="L55" class="blob-num js-line-number" data-line-number="55"></td>
        <td id="LC55" class="blob-code js-file-line">                &lt;/<span class="pl-ent">c:when</span>&gt;</td>
      </tr>
      <tr>
        <td id="L56" class="blob-num js-line-number" data-line-number="56"></td>
        <td id="LC56" class="blob-code js-file-line">                &lt;<span class="pl-ent">c:otherwise</span>&gt;</td>
      </tr>
      <tr>
        <td id="L57" class="blob-num js-line-number" data-line-number="57"></td>
        <td id="LC57" class="blob-code js-file-line">                  <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span></td>
      </tr>
      <tr>
        <td id="L58" class="blob-num js-line-number" data-line-number="58"></td>
        <td id="LC58" class="blob-code js-file-line">                &lt;/<span class="pl-ent">c:otherwise</span>&gt;</td>
      </tr>
      <tr>
        <td id="L59" class="blob-num js-line-number" data-line-number="59"></td>
        <td id="LC59" class="blob-code js-file-line">              &lt;/<span class="pl-ent">c:choose</span>&gt;</td>
      </tr>
      <tr>
        <td id="L60" class="blob-num js-line-number" data-line-number="60"></td>
        <td id="LC60" class="blob-code js-file-line">          &lt;/<span class="pl-ent">h2</span>&gt;</td>
      </tr>
      <tr>
        <td id="L61" class="blob-num js-line-number" data-line-number="61"></td>
        <td id="LC61" class="blob-code js-file-line">        &lt;/<span class="pl-ent">p</span>&gt;</td>
      </tr>
      <tr>
        <td id="L62" class="blob-num js-line-number" data-line-number="62"></td>
        <td id="LC62" class="blob-code js-file-line">        &lt;<span class="pl-ent">p</span>&gt;</td>
      </tr>
      <tr>
        <td id="L63" class="blob-num js-line-number" data-line-number="63"></td>
        <td id="LC63" class="blob-code js-file-line">          &lt;<span class="pl-ent">i</span>&gt;</td>
      </tr>
      <tr>
        <td id="L64" class="blob-num js-line-number" data-line-number="64"></td>
        <td id="LC64" class="blob-code js-file-line">            <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>heatmap.expressionScoreSummary<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span></td>
      </tr>
      <tr>
        <td id="L65" class="blob-num js-line-number" data-line-number="65"></td>
        <td id="LC65" class="blob-code js-file-line">            &lt;<span class="pl-ent">a</span> <span class="pl-e">href</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>/<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>webapp.path<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span>/experiment.do?experiment=Drosophila Cell Line and Developmental Stage Gene and Exon Scores<span class="pl-pds">&quot;</span></span>&gt; the Celniker group&lt;/<span class="pl-ent">a</span>&gt;</td>
      </tr>
      <tr>
        <td id="L66" class="blob-num js-line-number" data-line-number="66"></td>
        <td id="LC66" class="blob-code js-file-line">            and are log2 of the actual value.</td>
      </tr>
      <tr>
        <td id="L67" class="blob-num js-line-number" data-line-number="67"></td>
        <td id="LC67" class="blob-code js-file-line">            &lt;<span class="pl-ent">br</span>&gt;Heatmap visualization powered by</td>
      </tr>
      <tr>
        <td id="L68" class="blob-num js-line-number" data-line-number="68"></td>
        <td id="LC68" class="blob-code js-file-line">            &lt;<span class="pl-ent">a</span> <span class="pl-e">href</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>http://www.canvasxpress.org<span class="pl-pds">&quot;</span></span>&gt;canvasXpress&lt;/<span class="pl-ent">a</span>&gt;, learn more about the &lt;<span class="pl-ent">a</span> <span class="pl-e">href</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>http://www.canvasxpress.org/heatmap.html<span class="pl-pds">&quot;</span></span>&gt;display options&lt;/<span class="pl-ent">a</span>&gt;.</td>
      </tr>
      <tr>
        <td id="L69" class="blob-num js-line-number" data-line-number="69"></td>
        <td id="LC69" class="blob-code js-file-line">          &lt;/<span class="pl-ent">i</span>&gt;</td>
      </tr>
      <tr>
        <td id="L70" class="blob-num js-line-number" data-line-number="70"></td>
        <td id="LC70" class="blob-code js-file-line">        &lt;/<span class="pl-ent">p</span>&gt;</td>
      </tr>
      <tr>
        <td id="L71" class="blob-num js-line-number" data-line-number="71"></td>
        <td id="LC71" class="blob-code js-file-line">        &lt;<span class="pl-ent">br</span>/&gt;</td>
      </tr>
      <tr>
        <td id="L72" class="blob-num js-line-number" data-line-number="72"></td>
        <td id="LC72" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L73" class="blob-num js-line-number" data-line-number="73"></td>
        <td id="LC73" class="blob-code js-file-line">        &lt;<span class="pl-ent">html</span><span class="pl-e">:link</span> <span class="pl-e">linkName</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>#<span class="pl-pds">&quot;</span></span> <span class="pl-e">styleId</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>bro<span class="pl-pds">&quot;</span></span> <span class="pl-e">style</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>cursor:pointer<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L74" class="blob-num js-line-number" data-line-number="74"></td>
        <td id="LC74" class="blob-code js-file-line">        &lt;<span class="pl-ent">h3</span>&gt;</td>
      </tr>
      <tr>
        <td id="L75" class="blob-num js-line-number" data-line-number="75"></td>
        <td id="LC75" class="blob-code js-file-line">        &lt;<span class="pl-ent">c:if</span> <span class="pl-e">test</span>=<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">FeatureCount</span> <span class="pl-k">&gt;</span> <span class="pl-c1">MAX_DEFAULT_OPEN</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L76" class="blob-num js-line-number" data-line-number="76"></td>
        <td id="LC76" class="blob-code js-file-line">        Your list is big and there could be issues with the display:</td>
      </tr>
      <tr>
        <td id="L77" class="blob-num js-line-number" data-line-number="77"></td>
        <td id="LC77" class="blob-code js-file-line">        &lt;/<span class="pl-ent">c:if</span>&gt;</td>
      </tr>
      <tr>
        <td id="L78" class="blob-num js-line-number" data-line-number="78"></td>
        <td id="LC78" class="blob-code js-file-line">        &lt;<span class="pl-ent">b</span>&gt;Click to see/hide&lt;/<span class="pl-ent">b</span>&gt; the expression maps&lt;<span class="pl-ent">img</span> <span class="pl-e">src</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>images/undisclosed.gif<span class="pl-pds">&quot;</span></span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>oc<span class="pl-pds">&quot;</span></span>&gt;&lt;/<span class="pl-ent">h3</span>&gt;</td>
      </tr>
      <tr>
        <td id="L79" class="blob-num js-line-number" data-line-number="79"></td>
        <td id="LC79" class="blob-code js-file-line">        &lt;/<span class="pl-ent">html</span><span class="pl-e">:link</span>&gt;</td>
      </tr>
      <tr>
        <td id="L80" class="blob-num js-line-number" data-line-number="80"></td>
        <td id="LC80" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L81" class="blob-num js-line-number" data-line-number="81"></td>
        <td id="LC81" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L82" class="blob-num js-line-number" data-line-number="82"></td>
        <td id="LC82" class="blob-code js-file-line">        &lt;<span class="pl-ent">div</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>heatmapGraph<span class="pl-pds">&quot;</span></span> <span class="pl-e">style</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>display: block<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L83" class="blob-num js-line-number" data-line-number="83"></td>
        <td id="LC83" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L84" class="blob-num js-line-number" data-line-number="84"></td>
        <td id="LC84" class="blob-code js-file-line">        &lt;<span class="pl-ent">c:if</span> <span class="pl-e">test</span>=<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">FeatureCount</span> <span class="pl-k">&gt;</span> <span class="pl-c1">MAX_CLUSTER</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L85" class="blob-num js-line-number" data-line-number="85"></td>
        <td id="LC85" class="blob-code js-file-line">        Please note that clustering functions are not available for lists with more than <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">MAX_CLUSTER</span></span><span class="pl-pse"><span class="pl-s1">}</span></span> elements.</td>
      </tr>
      <tr>
        <td id="L86" class="blob-num js-line-number" data-line-number="86"></td>
        <td id="LC86" class="blob-code js-file-line">        &lt;<span class="pl-ent">br</span>&gt;</td>
      </tr>
      <tr>
        <td id="L87" class="blob-num js-line-number" data-line-number="87"></td>
        <td id="LC87" class="blob-code js-file-line">        &lt;/<span class="pl-ent">c:if</span>&gt;</td>
      </tr>
      <tr>
        <td id="L88" class="blob-num js-line-number" data-line-number="88"></td>
        <td id="LC88" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L89" class="blob-num js-line-number" data-line-number="89"></td>
        <td id="LC89" class="blob-code js-file-line">        &lt;<span class="pl-ent">div</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>heatmapContainer<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L90" class="blob-num js-line-number" data-line-number="90"></td>
        <td id="LC90" class="blob-code js-file-line">            &lt;<span class="pl-ent">table</span>&gt;</td>
      </tr>
      <tr>
        <td id="L91" class="blob-num js-line-number" data-line-number="91"></td>
        <td id="LC91" class="blob-code js-file-line">              &lt;<span class="pl-ent">tr</span>&gt;</td>
      </tr>
      <tr>
        <td id="L92" class="blob-num js-line-number" data-line-number="92"></td>
        <td id="LC92" class="blob-code js-file-line">                &lt;<span class="pl-ent">td</span>&gt;</td>
      </tr>
      <tr>
        <td id="L93" class="blob-num js-line-number" data-line-number="93"></td>
        <td id="LC93" class="blob-code js-file-line">                    &lt;<span class="pl-ent">div</span> <span class="pl-e">style</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>padding: 0px 0px 5px 30px;<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L94" class="blob-num js-line-number" data-line-number="94"></td>
        <td id="LC94" class="blob-code js-file-line">                     &lt;<span class="pl-ent">span</span>&gt;Cell Line Clustering - Hierarchical:&lt;/<span class="pl-ent">span</span>&gt;</td>
      </tr>
      <tr>
        <td id="L95" class="blob-num js-line-number" data-line-number="95"></td>
        <td id="LC95" class="blob-code js-file-line">                     &lt;<span class="pl-ent">select</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>cl-hc<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L96" class="blob-num js-line-number" data-line-number="96"></td>
        <td id="LC96" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>single<span class="pl-pds">&quot;</span></span> <span class="pl-e">selected</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>selected<span class="pl-pds">&quot;</span></span>&gt;Single&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L97" class="blob-num js-line-number" data-line-number="97"></td>
        <td id="LC97" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>complete<span class="pl-pds">&quot;</span></span>&gt;Complete&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L98" class="blob-num js-line-number" data-line-number="98"></td>
        <td id="LC98" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>average<span class="pl-pds">&quot;</span></span>&gt;Average&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L99" class="blob-num js-line-number" data-line-number="99"></td>
        <td id="LC99" class="blob-code js-file-line">                     &lt;/<span class="pl-ent">select</span>&gt;</td>
      </tr>
      <tr>
        <td id="L100" class="blob-num js-line-number" data-line-number="100"></td>
        <td id="LC100" class="blob-code js-file-line">                     &lt;<span class="pl-ent">span</span>&gt; and K-means:&lt;/<span class="pl-ent">span</span>&gt;</td>
      </tr>
      <tr>
        <td id="L101" class="blob-num js-line-number" data-line-number="101"></td>
        <td id="LC101" class="blob-code js-file-line">                     &lt;<span class="pl-ent">select</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>cl-km<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L102" class="blob-num js-line-number" data-line-number="102"></td>
        <td id="LC102" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>3<span class="pl-pds">&quot;</span></span> <span class="pl-e">selected</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>selected<span class="pl-pds">&quot;</span></span>&gt;3&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L103" class="blob-num js-line-number" data-line-number="103"></td>
        <td id="LC103" class="blob-code js-file-line">                     &lt;/<span class="pl-ent">select</span>&gt;</td>
      </tr>
      <tr>
        <td id="L104" class="blob-num js-line-number" data-line-number="104"></td>
        <td id="LC104" class="blob-code js-file-line">                    &lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L105" class="blob-num js-line-number" data-line-number="105"></td>
        <td id="LC105" class="blob-code js-file-line">                    &lt;<span class="pl-ent">canvas</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>canvas_cl<span class="pl-pds">&quot;</span></span> <span class="pl-e">width</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>525<span class="pl-pds">&quot;</span></span> <span class="pl-e">height</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>550<span class="pl-pds">&quot;</span></span>&gt;&lt;/<span class="pl-ent">canvas</span>&gt;</td>
      </tr>
      <tr>
        <td id="L106" class="blob-num js-line-number" data-line-number="106"></td>
        <td id="LC106" class="blob-code js-file-line">                &lt;/<span class="pl-ent">td</span>&gt;</td>
      </tr>
      <tr>
        <td id="L107" class="blob-num js-line-number" data-line-number="107"></td>
        <td id="LC107" class="blob-code js-file-line">                &lt;<span class="pl-ent">td</span>&gt;</td>
      </tr>
      <tr>
        <td id="L108" class="blob-num js-line-number" data-line-number="108"></td>
        <td id="LC108" class="blob-code js-file-line">                     &lt;<span class="pl-ent">div</span> <span class="pl-e">style</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>padding: 0px 0px 5px 30px;<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L109" class="blob-num js-line-number" data-line-number="109"></td>
        <td id="LC109" class="blob-code js-file-line">                     &lt;<span class="pl-ent">span</span>&gt;Developmental Stage Clustering - Hierarchical:&lt;/<span class="pl-ent">span</span>&gt;</td>
      </tr>
      <tr>
        <td id="L110" class="blob-num js-line-number" data-line-number="110"></td>
        <td id="LC110" class="blob-code js-file-line">                     &lt;<span class="pl-ent">select</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>ds-hc<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L111" class="blob-num js-line-number" data-line-number="111"></td>
        <td id="LC111" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>single<span class="pl-pds">&quot;</span></span> <span class="pl-e">selected</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>selected<span class="pl-pds">&quot;</span></span>&gt;Single&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L112" class="blob-num js-line-number" data-line-number="112"></td>
        <td id="LC112" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>complete<span class="pl-pds">&quot;</span></span>&gt;Complete&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L113" class="blob-num js-line-number" data-line-number="113"></td>
        <td id="LC113" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>average<span class="pl-pds">&quot;</span></span>&gt;Average&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L114" class="blob-num js-line-number" data-line-number="114"></td>
        <td id="LC114" class="blob-code js-file-line">                     &lt;/<span class="pl-ent">select</span>&gt;</td>
      </tr>
      <tr>
        <td id="L115" class="blob-num js-line-number" data-line-number="115"></td>
        <td id="LC115" class="blob-code js-file-line">                     &lt;<span class="pl-ent">span</span>&gt; and K-means:&lt;/<span class="pl-ent">span</span>&gt;</td>
      </tr>
      <tr>
        <td id="L116" class="blob-num js-line-number" data-line-number="116"></td>
        <td id="LC116" class="blob-code js-file-line">                     &lt;<span class="pl-ent">select</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>ds-km<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L117" class="blob-num js-line-number" data-line-number="117"></td>
        <td id="LC117" class="blob-code js-file-line">                         &lt;<span class="pl-ent">option</span> <span class="pl-e">value</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>3<span class="pl-pds">&quot;</span></span> <span class="pl-e">selected</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>selected<span class="pl-pds">&quot;</span></span>&gt;3&lt;/<span class="pl-ent">option</span>&gt;</td>
      </tr>
      <tr>
        <td id="L118" class="blob-num js-line-number" data-line-number="118"></td>
        <td id="LC118" class="blob-code js-file-line">                     &lt;/<span class="pl-ent">select</span>&gt;</td>
      </tr>
      <tr>
        <td id="L119" class="blob-num js-line-number" data-line-number="119"></td>
        <td id="LC119" class="blob-code js-file-line">                    &lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L120" class="blob-num js-line-number" data-line-number="120"></td>
        <td id="LC120" class="blob-code js-file-line">                     &lt;<span class="pl-ent">canvas</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>canvas_ds<span class="pl-pds">&quot;</span></span> <span class="pl-e">width</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>550<span class="pl-pds">&quot;</span></span> <span class="pl-e">height</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>550<span class="pl-pds">&quot;</span></span>&gt;&lt;/<span class="pl-ent">canvas</span>&gt;</td>
      </tr>
      <tr>
        <td id="L121" class="blob-num js-line-number" data-line-number="121"></td>
        <td id="LC121" class="blob-code js-file-line">                &lt;/<span class="pl-ent">td</span>&gt;</td>
      </tr>
      <tr>
        <td id="L122" class="blob-num js-line-number" data-line-number="122"></td>
        <td id="LC122" class="blob-code js-file-line">              &lt;/<span class="pl-ent">tr</span>&gt;</td>
      </tr>
      <tr>
        <td id="L123" class="blob-num js-line-number" data-line-number="123"></td>
        <td id="LC123" class="blob-code js-file-line">            &lt;/<span class="pl-ent">table</span>&gt;</td>
      </tr>
      <tr>
        <td id="L124" class="blob-num js-line-number" data-line-number="124"></td>
        <td id="LC124" class="blob-code js-file-line">        &lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L125" class="blob-num js-line-number" data-line-number="125"></td>
        <td id="LC125" class="blob-code js-file-line">        &lt;<span class="pl-ent">div</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>description_div<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L126" class="blob-num js-line-number" data-line-number="126"></td>
        <td id="LC126" class="blob-code js-file-line">            &lt;<span class="pl-ent">table</span> <span class="pl-e">border</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>0<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L127" class="blob-num js-line-number" data-line-number="127"></td>
        <td id="LC127" class="blob-code js-file-line">                &lt;<span class="pl-ent">tr</span>&gt;</td>
      </tr>
      <tr>
        <td id="L128" class="blob-num js-line-number" data-line-number="128"></td>
        <td id="LC128" class="blob-code js-file-line">                    &lt;<span class="pl-ent">td</span> &gt;&lt;<span class="pl-ent">h3</span> <span class="pl-e">style</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>font-weight: bold; background: black; color: white;<span class="pl-pds">&quot;</span></span>&gt;More Information&lt;/<span class="pl-ent">h3</span>&gt;&lt;/<span class="pl-ent">td</span>&gt;</td>
      </tr>
      <tr>
        <td id="L129" class="blob-num js-line-number" data-line-number="129"></td>
        <td id="LC129" class="blob-code js-file-line">                    &lt;<span class="pl-ent">td</span> &gt;&lt;<span class="pl-ent">h3</span> <span class="pl-e">style</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>background: white;<span class="pl-pds">&quot;</span></span>&gt;&lt;<span class="pl-ent">img</span> <span class="pl-e">src</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>images/disclosed.gif<span class="pl-pds">&quot;</span></span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>co<span class="pl-pds">&quot;</span></span>&gt;&lt;/<span class="pl-ent">h3</span>&gt;&lt;/<span class="pl-ent">td</span>&gt;</td>
      </tr>
      <tr>
        <td id="L130" class="blob-num js-line-number" data-line-number="130"></td>
        <td id="LC130" class="blob-code js-file-line">                &lt;/<span class="pl-ent">tr</span>&gt;</td>
      </tr>
      <tr>
        <td id="L131" class="blob-num js-line-number" data-line-number="131"></td>
        <td id="LC131" class="blob-code js-file-line">            &lt;/<span class="pl-ent">table</span>&gt;</td>
      </tr>
      <tr>
        <td id="L132" class="blob-num js-line-number" data-line-number="132"></td>
        <td id="LC132" class="blob-code js-file-line">        &lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L133" class="blob-num js-line-number" data-line-number="133"></td>
        <td id="LC133" class="blob-code js-file-line">        &lt;<span class="pl-ent">div</span> <span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>description<span class="pl-pds">&quot;</span></span> <span class="pl-e">style</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>padding: 5px<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L134" class="blob-num js-line-number" data-line-number="134"></td>
        <td id="LC134" class="blob-code js-file-line">            &lt;<span class="pl-ent">i</span>&gt;</td>
      </tr>
      <tr>
        <td id="L135" class="blob-num js-line-number" data-line-number="135"></td>
        <td id="LC135" class="blob-code js-file-line">              &lt;<span class="pl-ent">c:choose</span>&gt;</td>
      </tr>
      <tr>
        <td id="L136" class="blob-num js-line-number" data-line-number="136"></td>
        <td id="LC136" class="blob-code js-file-line">                &lt;<span class="pl-ent">c:when</span> <span class="pl-e">test</span>=<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&#39;</span>gene<span class="pl-pds">&#39;</span></span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L137" class="blob-num js-line-number" data-line-number="137"></td>
        <td id="LC137" class="blob-code js-file-line">                  <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>heatmap.geneExpressionScoreDescription<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span></td>
      </tr>
      <tr>
        <td id="L138" class="blob-num js-line-number" data-line-number="138"></td>
        <td id="LC138" class="blob-code js-file-line">                &lt;/<span class="pl-ent">c:when</span>&gt;</td>
      </tr>
      <tr>
        <td id="L139" class="blob-num js-line-number" data-line-number="139"></td>
        <td id="LC139" class="blob-code js-file-line">                &lt;<span class="pl-ent">c:when</span> <span class="pl-e">test</span>=<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&#39;</span>exon<span class="pl-pds">&#39;</span></span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L140" class="blob-num js-line-number" data-line-number="140"></td>
        <td id="LC140" class="blob-code js-file-line">                  <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>heatmap.exonExpressionScoreDescription<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span></td>
      </tr>
      <tr>
        <td id="L141" class="blob-num js-line-number" data-line-number="141"></td>
        <td id="LC141" class="blob-code js-file-line">                &lt;/<span class="pl-ent">c:when</span>&gt;</td>
      </tr>
      <tr>
        <td id="L142" class="blob-num js-line-number" data-line-number="142"></td>
        <td id="LC142" class="blob-code js-file-line">                &lt;<span class="pl-ent">c:otherwise</span>&gt;</td>
      </tr>
      <tr>
        <td id="L143" class="blob-num js-line-number" data-line-number="143"></td>
        <td id="LC143" class="blob-code js-file-line">                  <span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span></td>
      </tr>
      <tr>
        <td id="L144" class="blob-num js-line-number" data-line-number="144"></td>
        <td id="LC144" class="blob-code js-file-line">                &lt;/<span class="pl-ent">c:otherwise</span>&gt;</td>
      </tr>
      <tr>
        <td id="L145" class="blob-num js-line-number" data-line-number="145"></td>
        <td id="LC145" class="blob-code js-file-line">              &lt;/<span class="pl-ent">c:choose</span>&gt;</td>
      </tr>
      <tr>
        <td id="L146" class="blob-num js-line-number" data-line-number="146"></td>
        <td id="LC146" class="blob-code js-file-line">            &lt;<span class="pl-ent">br</span>&gt;Further information: check the &lt;<span class="pl-ent">a</span> <span class="pl-e">href</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>/<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>webapp.path<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span>/portal.do?class=Submission<span class="pl-ii">&amp;</span>externalids=modENCODE_3305<span class="pl-pds">&quot;</span></span>&gt;</td>
      </tr>
      <tr>
        <td id="L147" class="blob-num js-line-number" data-line-number="147"></td>
        <td id="LC147" class="blob-code js-file-line">modENCODE submission&lt;/<span class="pl-ent">a</span>&gt;, with links to the original score files for &lt;<span class="pl-ent">a</span> <span class="pl-e">href</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>http://submit.modencode.org/submit/public/get_file/3305/extracted/Drosophila_Cell_Lines_and_Developmental_Stages_Gene_Scores.txt<span class="pl-pds">&quot;</span></span> <span class="pl-e">target</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>_blank<span class="pl-pds">&quot;</span></span>&gt;genes&lt;/<span class="pl-ent">a</span>&gt;</td>
      </tr>
      <tr>
        <td id="L148" class="blob-num js-line-number" data-line-number="148"></td>
        <td id="LC148" class="blob-code js-file-line">            and &lt;<span class="pl-ent">a</span> <span class="pl-e">href</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>http://submit.modencode.org/submit/public/get_file/3305/extracted/Drosophila_Cell_Lines_and_Developmental_Stages_Exon_Scores.txt<span class="pl-pds">&quot;</span></span> <span class="pl-e">target</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>_blank<span class="pl-pds">&quot;</span></span>&gt;exons&lt;/<span class="pl-ent">a</span>&gt;.</td>
      </tr>
      <tr>
        <td id="L149" class="blob-num js-line-number" data-line-number="149"></td>
        <td id="LC149" class="blob-code js-file-line">            &lt;/<span class="pl-ent">i</span>&gt;</td>
      </tr>
      <tr>
        <td id="L150" class="blob-num js-line-number" data-line-number="150"></td>
        <td id="LC150" class="blob-code js-file-line">        &lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L151" class="blob-num js-line-number" data-line-number="151"></td>
        <td id="LC151" class="blob-code js-file-line">    &lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L152" class="blob-num js-line-number" data-line-number="152"></td>
        <td id="LC152" class="blob-code js-file-line">&lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L153" class="blob-num js-line-number" data-line-number="153"></td>
        <td id="LC153" class="blob-code js-file-line">&lt;/<span class="pl-ent">div</span>&gt;</td>
      </tr>
      <tr>
        <td id="L154" class="blob-num js-line-number" data-line-number="154"></td>
        <td id="LC154" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L155" class="blob-num js-line-number" data-line-number="155"></td>
        <td id="LC155" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L156" class="blob-num js-line-number" data-line-number="156"></td>
        <td id="LC156" class="blob-code js-file-line"><span class="pl-s1">&lt;<span class="pl-ent">script</span> <span class="pl-e">type</span>=<span class="pl-s"><span class="pl-pds">&quot;</span>text/javascript<span class="pl-pds">&quot;</span></span>&gt;</span></td>
      </tr>
      <tr>
        <td id="L157" class="blob-num js-line-number" data-line-number="157"></td>
        <td id="LC157" class="blob-code js-file-line"><span class="pl-s1"><span class="pl-k">var</span> feature_count <span class="pl-k">=</span> <span class="pl-c1">parseInt</span>(<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">FeatureCount</span></span><span class="pl-pse"><span class="pl-s1">}</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L158" class="blob-num js-line-number" data-line-number="158"></td>
        <td id="LC158" class="blob-code js-file-line"><span class="pl-s1"><span class="pl-k">var</span> max_cluster <span class="pl-k">=</span> <span class="pl-c1">parseInt</span>(<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">MAX_CLUSTER</span></span><span class="pl-pse"><span class="pl-s1">}</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L159" class="blob-num js-line-number" data-line-number="159"></td>
        <td id="LC159" class="blob-code js-file-line"><span class="pl-s1"><span class="pl-k">var</span> max_map <span class="pl-k">=</span> <span class="pl-c1">parseInt</span>(<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">MAX_MAP</span></span><span class="pl-pse"><span class="pl-s1">}</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L160" class="blob-num js-line-number" data-line-number="160"></td>
        <td id="LC160" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L161" class="blob-num js-line-number" data-line-number="161"></td>
        <td id="LC161" class="blob-code js-file-line"><span class="pl-s1">    <span class="pl-k">if</span> (<span class="pl-s"><span class="pl-pds">&#39;</span><span class="pl-pse">${</span><span class="pl-s1">fn<span class="pl-k">:</span>length(expressionScoreJSONCellLine)</span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&#39;</span></span> <span class="pl-k">&lt;</span> <span class="pl-c1">10</span>) {</span></td>
      </tr>
      <tr>
        <td id="L162" class="blob-num js-line-number" data-line-number="162"></td>
        <td id="LC162" class="blob-code js-file-line"><span class="pl-s1">        jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#heatmap_div<span class="pl-pds">&#39;</span></span>).<span class="pl-c1">remove</span>();</span></td>
      </tr>
      <tr>
        <td id="L163" class="blob-num js-line-number" data-line-number="163"></td>
        <td id="LC163" class="blob-code js-file-line"><span class="pl-s1">        jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#expression_div<span class="pl-pds">&#39;</span></span>).html(<span class="pl-s"><span class="pl-pds">&#39;</span>&lt;i&gt;Expression scores are not available&lt;/i&gt;<span class="pl-pds">&#39;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L164" class="blob-num js-line-number" data-line-number="164"></td>
        <td id="LC164" class="blob-code js-file-line"><span class="pl-s1">     } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L165" class="blob-num js-line-number" data-line-number="165"></td>
        <td id="LC165" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L166" class="blob-num js-line-number" data-line-number="166"></td>
        <td id="LC166" class="blob-code js-file-line"><span class="pl-s1">         <span class="pl-k">if</span> (feature_count <span class="pl-k">&gt;</span> max_map) {</span></td>
      </tr>
      <tr>
        <td id="L167" class="blob-num js-line-number" data-line-number="167"></td>
        <td id="LC167" class="blob-code js-file-line"><span class="pl-s1">             jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#heatmap_div<span class="pl-pds">&#39;</span></span>).<span class="pl-c1">remove</span>();</span></td>
      </tr>
      <tr>
        <td id="L168" class="blob-num js-line-number" data-line-number="168"></td>
        <td id="LC168" class="blob-code js-file-line"><span class="pl-s1">             jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#expression_div<span class="pl-pds">&#39;</span></span>).html(<span class="pl-s"><span class="pl-pds">&#39;</span>&lt;i&gt;Too many elements, please select a subset to see the heat maps.&lt;/i&gt;<span class="pl-pds">&#39;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L169" class="blob-num js-line-number" data-line-number="169"></td>
        <td id="LC169" class="blob-code js-file-line"><span class="pl-s1">         }</span></td>
      </tr>
      <tr>
        <td id="L170" class="blob-num js-line-number" data-line-number="170"></td>
        <td id="LC170" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L171" class="blob-num js-line-number" data-line-number="171"></td>
        <td id="LC171" class="blob-code js-file-line"><span class="pl-s1">         jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#description<span class="pl-pds">&quot;</span></span>).hide();</span></td>
      </tr>
      <tr>
        <td id="L172" class="blob-num js-line-number" data-line-number="172"></td>
        <td id="LC172" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L173" class="blob-num js-line-number" data-line-number="173"></td>
        <td id="LC173" class="blob-code js-file-line"><span class="pl-s1">         jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#description_div<span class="pl-pds">&quot;</span></span>).<span class="pl-c1">click</span>(<span class="pl-k">function</span> () {</span></td>
      </tr>
      <tr>
        <td id="L174" class="blob-num js-line-number" data-line-number="174"></td>
        <td id="LC174" class="blob-code js-file-line"><span class="pl-s1">               <span class="pl-k">if</span>(jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#description<span class="pl-pds">&quot;</span></span>).is(<span class="pl-s"><span class="pl-pds">&quot;</span>:hidden<span class="pl-pds">&quot;</span></span>)) {</span></td>
      </tr>
      <tr>
        <td id="L175" class="blob-num js-line-number" data-line-number="175"></td>
        <td id="LC175" class="blob-code js-file-line"><span class="pl-s1">                 jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#co<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&quot;</span>src<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>images/disclosed.gif<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L176" class="blob-num js-line-number" data-line-number="176"></td>
        <td id="LC176" class="blob-code js-file-line"><span class="pl-s1">               } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L177" class="blob-num js-line-number" data-line-number="177"></td>
        <td id="LC177" class="blob-code js-file-line"><span class="pl-s1">                 jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#co<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&quot;</span>src<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>images/undisclosed.gif<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L178" class="blob-num js-line-number" data-line-number="178"></td>
        <td id="LC178" class="blob-code js-file-line"><span class="pl-s1">               }</span></td>
      </tr>
      <tr>
        <td id="L179" class="blob-num js-line-number" data-line-number="179"></td>
        <td id="LC179" class="blob-code js-file-line"><span class="pl-s1">               jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#description<span class="pl-pds">&quot;</span></span>).toggle(<span class="pl-s"><span class="pl-pds">&quot;</span>slow<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L180" class="blob-num js-line-number" data-line-number="180"></td>
        <td id="LC180" class="blob-code js-file-line"><span class="pl-s1">            });</span></td>
      </tr>
      <tr>
        <td id="L181" class="blob-num js-line-number" data-line-number="181"></td>
        <td id="LC181" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L182" class="blob-num js-line-number" data-line-number="182"></td>
        <td id="LC182" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L183" class="blob-num js-line-number" data-line-number="183"></td>
        <td id="LC183" class="blob-code js-file-line"><span class="pl-s1">           <span class="pl-c">// hm - heatmap; cl - cellline; ds - developmentalstage; hc - hierarchical clustering; km - kmeans</span></span></td>
      </tr>
      <tr>
        <td id="L184" class="blob-num js-line-number" data-line-number="184"></td>
        <td id="LC184" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-k">var</span> hm_cl <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-en">CanvasXpress</span>(<span class="pl-s"><span class="pl-pds">&#39;</span>canvas_cl<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L185" class="blob-num js-line-number" data-line-number="185"></td>
        <td id="LC185" class="blob-code js-file-line"><span class="pl-s1">                                         <span class="pl-pse">${</span><span class="pl-s1">expressionScoreJSONCellLine</span><span class="pl-pse"><span class="pl-s1">}</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L186" class="blob-num js-line-number" data-line-number="186"></td>
        <td id="LC186" class="blob-code js-file-line"><span class="pl-s1">                                         {graphType<span class="pl-k">:</span> <span class="pl-s"><span class="pl-pds">&#39;</span>Heatmap<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L187" class="blob-num js-line-number" data-line-number="187"></td>
        <td id="LC187" class="blob-code js-file-line"><span class="pl-s1">                                          title<span class="pl-k">:</span> <span class="pl-s"><span class="pl-pds">&#39;</span>Cell Line<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L188" class="blob-num js-line-number" data-line-number="188"></td>
        <td id="LC188" class="blob-code js-file-line"><span class="pl-s1">                                          <span class="pl-c">// heatmapType: &#39;yellow-purple&#39;,</span></span></td>
      </tr>
      <tr>
        <td id="L189" class="blob-num js-line-number" data-line-number="189"></td>
        <td id="LC189" class="blob-code js-file-line"><span class="pl-s1">                                          dendrogramSpace<span class="pl-k">:</span> <span class="pl-c1">6</span>,</span></td>
      </tr>
      <tr>
        <td id="L190" class="blob-num js-line-number" data-line-number="190"></td>
        <td id="LC190" class="blob-code js-file-line"><span class="pl-s1">                                          smpDendrogramPosition<span class="pl-k">:</span> <span class="pl-s"><span class="pl-pds">&#39;</span>right<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L191" class="blob-num js-line-number" data-line-number="191"></td>
        <td id="LC191" class="blob-code js-file-line"><span class="pl-s1">                                          varDendrogramPosition<span class="pl-k">:</span> <span class="pl-s"><span class="pl-pds">&#39;</span>bottom<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L192" class="blob-num js-line-number" data-line-number="192"></td>
        <td id="LC192" class="blob-code js-file-line"><span class="pl-s1">                                          setMin<span class="pl-k">:</span> <span class="pl-pse">${</span><span class="pl-s1">minExpressionScore</span><span class="pl-pse"><span class="pl-s1">}</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L193" class="blob-num js-line-number" data-line-number="193"></td>
        <td id="LC193" class="blob-code js-file-line"><span class="pl-s1">                                          setMax<span class="pl-k">:</span> <span class="pl-pse">${</span><span class="pl-s1">maxExpressionScore</span><span class="pl-pse"><span class="pl-s1">}</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L194" class="blob-num js-line-number" data-line-number="194"></td>
        <td id="LC194" class="blob-code js-file-line"><span class="pl-s1">                                          varLabelRotate<span class="pl-k">:</span> <span class="pl-c1">45</span>,</span></td>
      </tr>
      <tr>
        <td id="L195" class="blob-num js-line-number" data-line-number="195"></td>
        <td id="LC195" class="blob-code js-file-line"><span class="pl-s1">                                          centerData<span class="pl-k">:</span> <span class="pl-c1">false</span>,</span></td>
      </tr>
      <tr>
        <td id="L196" class="blob-num js-line-number" data-line-number="196"></td>
        <td id="LC196" class="blob-code js-file-line"><span class="pl-s1">                                          autoExtend<span class="pl-k">:</span> <span class="pl-c1">true</span>},</span></td>
      </tr>
      <tr>
        <td id="L197" class="blob-num js-line-number" data-line-number="197"></td>
        <td id="LC197" class="blob-code js-file-line"><span class="pl-s1">                                          {<span class="pl-en">click</span>: <span class="pl-k">function</span>(<span class="pl-smi">o</span>) {</span></td>
      </tr>
      <tr>
        <td id="L198" class="blob-num js-line-number" data-line-number="198"></td>
        <td id="LC198" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-k">var</span> featureId <span class="pl-k">=</span> o.<span class="pl-c1">y</span>.smps;</span></td>
      </tr>
      <tr>
        <td id="L199" class="blob-num js-line-number" data-line-number="199"></td>
        <td id="LC199" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-k">var</span> condition <span class="pl-k">=</span> o.<span class="pl-c1">y</span>.vars;</span></td>
      </tr>
      <tr>
        <td id="L200" class="blob-num js-line-number" data-line-number="200"></td>
        <td id="LC200" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L201" class="blob-num js-line-number" data-line-number="201"></td>
        <td id="LC201" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-k">if</span> (<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&quot;</span>gene<span class="pl-pds">&quot;</span></span>) {</span></td>
      </tr>
      <tr>
        <td id="L202" class="blob-num js-line-number" data-line-number="202"></td>
        <td id="LC202" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L203" class="blob-num js-line-number" data-line-number="203"></td>
        <td id="LC203" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> query <span class="pl-k">=</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&lt;query name=&quot;&quot; model=&quot;genomic&quot; view=&quot;GeneExpressionScore.score GeneExpressionScore.cellLine.name GeneExpressionScore.gene.primaryIdentifier GeneExpressionScore.gene.secondaryIdentifier GeneExpressionScore.gene.symbol GeneExpressionScore.gene.name GeneExpressionScore.gene.source GeneExpressionScore.organism.shortName GeneExpressionScore.submission.title GeneExpressionScore.submission.design GeneExpressionScore.submission.DCCid&quot; sortOrder=&quot;GeneExpressionScore.score asc&quot; constraintLogic=&quot;A and B&quot;&gt;&lt;constraint path=&quot;GeneExpressionScore.gene&quot; code=&quot;B&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> featureId <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot; extraValue=&quot;&quot;/&gt;&lt;constraint path=&quot;GeneExpressionScore.cellLine&quot; code=&quot;A&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> condition <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot;/&gt;&lt;/query&gt;<span class="pl-pds">&#39;</span></span>;</span></td>
      </tr>
      <tr>
        <td id="L204" class="blob-num js-line-number" data-line-number="204"></td>
        <td id="LC204" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> encodedQuery <span class="pl-k">=</span> encodeURIComponent(query);</span></td>
      </tr>
      <tr>
        <td id="L205" class="blob-num js-line-number" data-line-number="205"></td>
        <td id="LC205" class="blob-code js-file-line"><span class="pl-s1">                                                       encodedQuery <span class="pl-k">=</span> encodedQuery.<span class="pl-c1">replace</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>%20<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>+<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L206" class="blob-num js-line-number" data-line-number="206"></td>
        <td id="LC206" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-c1">window</span>.<span class="pl-c1">open</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>/<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>webapp.path<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span>/loadQuery.do?skipBuilder=true&amp;query=<span class="pl-pds">&quot;</span></span> <span class="pl-k">+</span> encodedQuery <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&quot;</span>%0A++++++++++++&amp;trail=|query&amp;method=xml<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L207" class="blob-num js-line-number" data-line-number="207"></td>
        <td id="LC207" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L208" class="blob-num js-line-number" data-line-number="208"></td>
        <td id="LC208" class="blob-code js-file-line"><span class="pl-s1">                                                   } <span class="pl-k">else</span> <span class="pl-k">if</span> (<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&quot;</span>exon<span class="pl-pds">&quot;</span></span>) {</span></td>
      </tr>
      <tr>
        <td id="L209" class="blob-num js-line-number" data-line-number="209"></td>
        <td id="LC209" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L210" class="blob-num js-line-number" data-line-number="210"></td>
        <td id="LC210" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> query <span class="pl-k">=</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&lt;query name=&quot;&quot; model=&quot;genomic&quot; view=&quot;ExonExpressionScore.score ExonExpressionScore.cellLine.name ExonExpressionScore.exon.primaryIdentifier ExonExpressionScore.exon.symbol  ExonExpressionScore.exon.gene.primaryIdentifier ExonExpressionScore.exon.gene.symbol ExonExpressionScore.organism.shortName ExonExpressionScore.submission.title ExonExpressionScore.submission.design ExonExpressionScore.submission.DCCid&quot; sortOrder=&quot;ExonExpressionScore.exon.primaryIdentifier asc&quot; constraintLogic=&quot;A and B&quot;&gt;&lt;constraint path=&quot;ExonExpressionScore.exon&quot; code=&quot;A&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> featureId <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot; extraValue=&quot;&quot;/&gt;&lt;constraint path=&quot;ExonExpressionScore.cellLine&quot; code=&quot;B&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> condition <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot; extraValue=&quot;&quot;/&gt;&lt;/query&gt;<span class="pl-pds">&#39;</span></span>;</span></td>
      </tr>
      <tr>
        <td id="L211" class="blob-num js-line-number" data-line-number="211"></td>
        <td id="LC211" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> encodedQuery <span class="pl-k">=</span> encodeURIComponent(query);</span></td>
      </tr>
      <tr>
        <td id="L212" class="blob-num js-line-number" data-line-number="212"></td>
        <td id="LC212" class="blob-code js-file-line"><span class="pl-s1">                                                       encodedQuery <span class="pl-k">=</span> encodedQuery.<span class="pl-c1">replace</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>%20<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>+<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L213" class="blob-num js-line-number" data-line-number="213"></td>
        <td id="LC213" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-c1">window</span>.<span class="pl-c1">open</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>/<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>webapp.path<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span>/loadQuery.do?skipBuilder=true&amp;query=<span class="pl-pds">&quot;</span></span> <span class="pl-k">+</span> encodedQuery <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&quot;</span>%0A++++++++++++&amp;trail=|query&amp;method=xml<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L214" class="blob-num js-line-number" data-line-number="214"></td>
        <td id="LC214" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L215" class="blob-num js-line-number" data-line-number="215"></td>
        <td id="LC215" class="blob-code js-file-line"><span class="pl-s1">                                                   } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L216" class="blob-num js-line-number" data-line-number="216"></td>
        <td id="LC216" class="blob-code js-file-line"><span class="pl-s1">                                                      <span class="pl-c1">alert</span>(<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L217" class="blob-num js-line-number" data-line-number="217"></td>
        <td id="LC217" class="blob-code js-file-line"><span class="pl-s1">                                                   }</span></td>
      </tr>
      <tr>
        <td id="L218" class="blob-num js-line-number" data-line-number="218"></td>
        <td id="LC218" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-c">// window.open(&#39;/${WEB_PROPERTIES[&#39;webapp.path&#39;]}/portal.do?class=Gene&amp;externalids=&#39; + o.y.smps);</span></span></td>
      </tr>
      <tr>
        <td id="L219" class="blob-num js-line-number" data-line-number="219"></td>
        <td id="LC219" class="blob-code js-file-line"><span class="pl-s1">                                                  }}</span></td>
      </tr>
      <tr>
        <td id="L220" class="blob-num js-line-number" data-line-number="220"></td>
        <td id="LC220" class="blob-code js-file-line"><span class="pl-s1">                                         );</span></td>
      </tr>
      <tr>
        <td id="L221" class="blob-num js-line-number" data-line-number="221"></td>
        <td id="LC221" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-c">// cluster on gene/exons</span></span></td>
      </tr>
      <tr>
        <td id="L222" class="blob-num js-line-number" data-line-number="222"></td>
        <td id="LC222" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-k">if</span> (feature_count <span class="pl-k">&gt;</span> max_cluster) {</span></td>
      </tr>
      <tr>
        <td id="L223" class="blob-num js-line-number" data-line-number="223"></td>
        <td id="LC223" class="blob-code js-file-line"><span class="pl-s1">                jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#cl-hc<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&#39;</span>disabled<span class="pl-pds">&#39;</span></span>, <span class="pl-c1">true</span>);</span></td>
      </tr>
      <tr>
        <td id="L224" class="blob-num js-line-number" data-line-number="224"></td>
        <td id="LC224" class="blob-code js-file-line"><span class="pl-s1">            }</span></td>
      </tr>
      <tr>
        <td id="L225" class="blob-num js-line-number" data-line-number="225"></td>
        <td id="LC225" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L226" class="blob-num js-line-number" data-line-number="226"></td>
        <td id="LC226" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-k">if</span> (feature_count <span class="pl-k">&gt;</span> <span class="pl-c1">3</span> <span class="pl-k">&amp;&amp;</span> feature_count <span class="pl-k">&lt;=</span> max_cluster) {</span></td>
      </tr>
      <tr>
        <td id="L227" class="blob-num js-line-number" data-line-number="227"></td>
        <td id="LC227" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.clusterSamples();</span></td>
      </tr>
      <tr>
        <td id="L228" class="blob-num js-line-number" data-line-number="228"></td>
        <td id="LC228" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.kmeansSamples();</span></td>
      </tr>
      <tr>
        <td id="L229" class="blob-num js-line-number" data-line-number="229"></td>
        <td id="LC229" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L230" class="blob-num js-line-number" data-line-number="230"></td>
        <td id="LC230" class="blob-code js-file-line"><span class="pl-s1">                <span class="pl-k">for</span> (<span class="pl-k">var</span> i<span class="pl-k">=</span><span class="pl-c1">4</span>; i <span class="pl-k">&lt;</span> feature_count; <span class="pl-k">++</span>i) {</span></td>
      </tr>
      <tr>
        <td id="L231" class="blob-num js-line-number" data-line-number="231"></td>
        <td id="LC231" class="blob-code js-file-line"><span class="pl-s1">                    jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#cl-km<span class="pl-pds">&#39;</span></span>).</span></td>
      </tr>
      <tr>
        <td id="L232" class="blob-num js-line-number" data-line-number="232"></td>
        <td id="LC232" class="blob-code js-file-line"><span class="pl-s1">                              append(jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>&lt;option&gt;&lt;/option&gt;<span class="pl-pds">&quot;</span></span>).</span></td>
      </tr>
      <tr>
        <td id="L233" class="blob-num js-line-number" data-line-number="233"></td>
        <td id="LC233" class="blob-code js-file-line"><span class="pl-s1">                              attr(<span class="pl-s"><span class="pl-pds">&quot;</span>value<span class="pl-pds">&quot;</span></span>,i).</span></td>
      </tr>
      <tr>
        <td id="L234" class="blob-num js-line-number" data-line-number="234"></td>
        <td id="LC234" class="blob-code js-file-line"><span class="pl-s1">                              text(i));</span></td>
      </tr>
      <tr>
        <td id="L235" class="blob-num js-line-number" data-line-number="235"></td>
        <td id="LC235" class="blob-code js-file-line"><span class="pl-s1">                }</span></td>
      </tr>
      <tr>
        <td id="L236" class="blob-num js-line-number" data-line-number="236"></td>
        <td id="LC236" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L237" class="blob-num js-line-number" data-line-number="237"></td>
        <td id="LC237" class="blob-code js-file-line"><span class="pl-s1">            } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L238" class="blob-num js-line-number" data-line-number="238"></td>
        <td id="LC238" class="blob-code js-file-line"><span class="pl-s1">                jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#cl-km<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&#39;</span>disabled<span class="pl-pds">&#39;</span></span>, <span class="pl-c1">true</span>);</span></td>
      </tr>
      <tr>
        <td id="L239" class="blob-num js-line-number" data-line-number="239"></td>
        <td id="LC239" class="blob-code js-file-line"><span class="pl-s1">            }</span></td>
      </tr>
      <tr>
        <td id="L240" class="blob-num js-line-number" data-line-number="240"></td>
        <td id="LC240" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L241" class="blob-num js-line-number" data-line-number="241"></td>
        <td id="LC241" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-c">// cluster on conditions</span></span></td>
      </tr>
      <tr>
        <td id="L242" class="blob-num js-line-number" data-line-number="242"></td>
        <td id="LC242" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-k">if</span> (feature_count <span class="pl-k">&lt;=</span> max_cluster) {</span></td>
      </tr>
      <tr>
        <td id="L243" class="blob-num js-line-number" data-line-number="243"></td>
        <td id="LC243" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.clusterVariables(); <span class="pl-c">// clustering method will call draw action within it.</span></span></td>
      </tr>
      <tr>
        <td id="L244" class="blob-num js-line-number" data-line-number="244"></td>
        <td id="LC244" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.draw();</span></td>
      </tr>
      <tr>
        <td id="L245" class="blob-num js-line-number" data-line-number="245"></td>
        <td id="LC245" class="blob-code js-file-line"><span class="pl-s1">            }</span></td>
      </tr>
      <tr>
        <td id="L246" class="blob-num js-line-number" data-line-number="246"></td>
        <td id="LC246" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-c">// cx_cellline.kmeansVariables();</span></span></td>
      </tr>
      <tr>
        <td id="L247" class="blob-num js-line-number" data-line-number="247"></td>
        <td id="LC247" class="blob-code js-file-line"><span class="pl-s1"><span class="pl-c">//            hm_cl.draw();</span></span></td>
      </tr>
      <tr>
        <td id="L248" class="blob-num js-line-number" data-line-number="248"></td>
        <td id="LC248" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L249" class="blob-num js-line-number" data-line-number="249"></td>
        <td id="LC249" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-k">var</span> hm_ds <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-en">CanvasXpress</span>(<span class="pl-s"><span class="pl-pds">&#39;</span>canvas_ds<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L250" class="blob-num js-line-number" data-line-number="250"></td>
        <td id="LC250" class="blob-code js-file-line"><span class="pl-s1">                                         <span class="pl-pse">${</span><span class="pl-s1">expressionScoreJSONDevelopmentalStage</span><span class="pl-pse"><span class="pl-s1">}</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L251" class="blob-num js-line-number" data-line-number="251"></td>
        <td id="LC251" class="blob-code js-file-line"><span class="pl-s1">                                         {graphType<span class="pl-k">:</span> <span class="pl-s"><span class="pl-pds">&#39;</span>Heatmap<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L252" class="blob-num js-line-number" data-line-number="252"></td>
        <td id="LC252" class="blob-code js-file-line"><span class="pl-s1">                                          title<span class="pl-k">:</span> <span class="pl-s"><span class="pl-pds">&#39;</span>Developmental Stage<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L253" class="blob-num js-line-number" data-line-number="253"></td>
        <td id="LC253" class="blob-code js-file-line"><span class="pl-s1">                                          <span class="pl-c">// heatmapType: &#39;yellow-purple&#39;,</span></span></td>
      </tr>
      <tr>
        <td id="L254" class="blob-num js-line-number" data-line-number="254"></td>
        <td id="LC254" class="blob-code js-file-line"><span class="pl-s1">                                          dendrogramSpace<span class="pl-k">:</span> <span class="pl-c1">6</span>,</span></td>
      </tr>
      <tr>
        <td id="L255" class="blob-num js-line-number" data-line-number="255"></td>
        <td id="LC255" class="blob-code js-file-line"><span class="pl-s1">                                          smpDendrogramPosition<span class="pl-k">:</span> <span class="pl-s"><span class="pl-pds">&#39;</span>right<span class="pl-pds">&#39;</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L256" class="blob-num js-line-number" data-line-number="256"></td>
        <td id="LC256" class="blob-code js-file-line"><span class="pl-s1">                                          setMin<span class="pl-k">:</span> <span class="pl-pse">${</span><span class="pl-s1">minExpressionScore</span><span class="pl-pse"><span class="pl-s1">}</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L257" class="blob-num js-line-number" data-line-number="257"></td>
        <td id="LC257" class="blob-code js-file-line"><span class="pl-s1">                                          setMax<span class="pl-k">:</span> <span class="pl-pse">${</span><span class="pl-s1">maxExpressionScore</span><span class="pl-pse"><span class="pl-s1">}</span></span>,</span></td>
      </tr>
      <tr>
        <td id="L258" class="blob-num js-line-number" data-line-number="258"></td>
        <td id="LC258" class="blob-code js-file-line"><span class="pl-s1">                                          varLabelRotate<span class="pl-k">:</span> <span class="pl-c1">45</span>,</span></td>
      </tr>
      <tr>
        <td id="L259" class="blob-num js-line-number" data-line-number="259"></td>
        <td id="LC259" class="blob-code js-file-line"><span class="pl-s1">                                          centerData<span class="pl-k">:</span> <span class="pl-c1">false</span>,</span></td>
      </tr>
      <tr>
        <td id="L260" class="blob-num js-line-number" data-line-number="260"></td>
        <td id="LC260" class="blob-code js-file-line"><span class="pl-s1">                                          autoExtend<span class="pl-k">:</span> <span class="pl-c1">true</span>},</span></td>
      </tr>
      <tr>
        <td id="L261" class="blob-num js-line-number" data-line-number="261"></td>
        <td id="LC261" class="blob-code js-file-line"><span class="pl-s1">                                          {<span class="pl-en">click</span>: <span class="pl-k">function</span>(<span class="pl-smi">o</span>) {</span></td>
      </tr>
      <tr>
        <td id="L262" class="blob-num js-line-number" data-line-number="262"></td>
        <td id="LC262" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-k">var</span> featureId <span class="pl-k">=</span> o.<span class="pl-c1">y</span>.smps;</span></td>
      </tr>
      <tr>
        <td id="L263" class="blob-num js-line-number" data-line-number="263"></td>
        <td id="LC263" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-k">var</span> condition <span class="pl-k">=</span> o.<span class="pl-c1">y</span>.vars;</span></td>
      </tr>
      <tr>
        <td id="L264" class="blob-num js-line-number" data-line-number="264"></td>
        <td id="LC264" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L265" class="blob-num js-line-number" data-line-number="265"></td>
        <td id="LC265" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-k">if</span> (<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&quot;</span>gene<span class="pl-pds">&quot;</span></span>) {</span></td>
      </tr>
      <tr>
        <td id="L266" class="blob-num js-line-number" data-line-number="266"></td>
        <td id="LC266" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L267" class="blob-num js-line-number" data-line-number="267"></td>
        <td id="LC267" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> query <span class="pl-k">=</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&lt;query name=&quot;&quot; model=&quot;genomic&quot; view=&quot;GeneExpressionScore.score GeneExpressionScore.developmentalStage.name GeneExpressionScore.gene.primaryIdentifier GeneExpressionScore.gene.secondaryIdentifier GeneExpressionScore.gene.symbol GeneExpressionScore.gene.name GeneExpressionScore.gene.source GeneExpressionScore.organism.shortName GeneExpressionScore.submission.title GeneExpressionScore.submission.design GeneExpressionScore.submission.DCCid&quot; sortOrder=&quot;GeneExpressionScore.score asc&quot; constraintLogic=&quot;A and B&quot;&gt;&lt;constraint path=&quot;GeneExpressionScore.gene&quot; code=&quot;B&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> featureId <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot; extraValue=&quot;&quot;/&gt;&lt;constraint path=&quot;GeneExpressionScore.developmentalStage&quot; code=&quot;A&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> condition <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot;/&gt;&lt;/query&gt;<span class="pl-pds">&#39;</span></span>;</span></td>
      </tr>
      <tr>
        <td id="L268" class="blob-num js-line-number" data-line-number="268"></td>
        <td id="LC268" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> encodedQuery <span class="pl-k">=</span> encodeURIComponent(query);</span></td>
      </tr>
      <tr>
        <td id="L269" class="blob-num js-line-number" data-line-number="269"></td>
        <td id="LC269" class="blob-code js-file-line"><span class="pl-s1">                                                       encodedQuery <span class="pl-k">=</span> encodedQuery.<span class="pl-c1">replace</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>%20<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>+<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L270" class="blob-num js-line-number" data-line-number="270"></td>
        <td id="LC270" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-c1">window</span>.<span class="pl-c1">open</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>/<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>webapp.path<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span>/loadQuery.do?skipBuilder=true&amp;query=<span class="pl-pds">&quot;</span></span> <span class="pl-k">+</span> encodedQuery <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&quot;</span>%0A++++++++++++&amp;trail=|query&amp;method=xml<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L271" class="blob-num js-line-number" data-line-number="271"></td>
        <td id="LC271" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L272" class="blob-num js-line-number" data-line-number="272"></td>
        <td id="LC272" class="blob-code js-file-line"><span class="pl-s1">                                                   } <span class="pl-k">else</span> <span class="pl-k">if</span> (<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span> <span class="pl-k">==</span> <span class="pl-s"><span class="pl-pds">&quot;</span>exon<span class="pl-pds">&quot;</span></span>) {</span></td>
      </tr>
      <tr>
        <td id="L273" class="blob-num js-line-number" data-line-number="273"></td>
        <td id="LC273" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L274" class="blob-num js-line-number" data-line-number="274"></td>
        <td id="LC274" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> query <span class="pl-k">=</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&lt;query name=&quot;&quot; model=&quot;genomic&quot; view=&quot;ExonExpressionScore.score ExonExpressionScore.developmentalStage.name ExonExpressionScore.exon.primaryIdentifier ExonExpressionScore.exon.symbol  ExonExpressionScore.exon.gene.primaryIdentifier ExonExpressionScore.exon.gene.symbol ExonExpressionScore.organism.shortName ExonExpressionScore.submission.title ExonExpressionScore.submission.design ExonExpressionScore.submission.DCCid&quot; sortOrder=&quot;ExonExpressionScore.exon.primaryIdentifier asc&quot; constraintLogic=&quot;A and B&quot;&gt;&lt;constraint path=&quot;ExonExpressionScore.exon&quot; code=&quot;A&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> featureId <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot; extraValue=&quot;&quot;/&gt;&lt;constraint path=&quot;ExonExpressionScore.developmentalStage&quot; code=&quot;B&quot; op=&quot;LOOKUP&quot; value=&quot;<span class="pl-pds">&#39;</span></span> <span class="pl-k">+</span> condition <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&#39;</span>&quot; extraValue=&quot;&quot;/&gt;&lt;/query&gt;<span class="pl-pds">&#39;</span></span>;</span></td>
      </tr>
      <tr>
        <td id="L275" class="blob-num js-line-number" data-line-number="275"></td>
        <td id="LC275" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-k">var</span> encodedQuery <span class="pl-k">=</span> encodeURIComponent(query);</span></td>
      </tr>
      <tr>
        <td id="L276" class="blob-num js-line-number" data-line-number="276"></td>
        <td id="LC276" class="blob-code js-file-line"><span class="pl-s1">                                                       encodedQuery <span class="pl-k">=</span> encodedQuery.<span class="pl-c1">replace</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>%20<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>+<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L277" class="blob-num js-line-number" data-line-number="277"></td>
        <td id="LC277" class="blob-code js-file-line"><span class="pl-s1">                                                       <span class="pl-c1">window</span>.<span class="pl-c1">open</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>/<span class="pl-pse">${</span><span class="pl-s1"><span class="pl-c1">WEB_PROPERTIES</span>[<span class="pl-s"><span class="pl-pds">&#39;</span>webapp.path<span class="pl-pds">&#39;</span></span>]</span><span class="pl-pse"><span class="pl-s1">}</span></span>/loadQuery.do?skipBuilder=true&amp;query=<span class="pl-pds">&quot;</span></span> <span class="pl-k">+</span> encodedQuery <span class="pl-k">+</span> <span class="pl-s"><span class="pl-pds">&quot;</span>%0A++++++++++++&amp;trail=|query&amp;method=xml<span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L278" class="blob-num js-line-number" data-line-number="278"></td>
        <td id="LC278" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L279" class="blob-num js-line-number" data-line-number="279"></td>
        <td id="LC279" class="blob-code js-file-line"><span class="pl-s1">                                                   } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L280" class="blob-num js-line-number" data-line-number="280"></td>
        <td id="LC280" class="blob-code js-file-line"><span class="pl-s1">                                                      <span class="pl-c1">alert</span>(<span class="pl-s"><span class="pl-pds">&quot;</span><span class="pl-pse">${</span><span class="pl-s1"><span class="pl-smi">ExpressionType</span></span><span class="pl-pse"><span class="pl-s1">}</span></span><span class="pl-pds">&quot;</span></span>);</span></td>
      </tr>
      <tr>
        <td id="L281" class="blob-num js-line-number" data-line-number="281"></td>
        <td id="LC281" class="blob-code js-file-line"><span class="pl-s1">                                                   }</span></td>
      </tr>
      <tr>
        <td id="L282" class="blob-num js-line-number" data-line-number="282"></td>
        <td id="LC282" class="blob-code js-file-line"><span class="pl-s1">                                                   <span class="pl-c">// window.open(&#39;/${WEB_PROPERTIES[&#39;webapp.path&#39;]}/portal.do?class=Gene&amp;externalids=&#39; + o.y.smps);</span></span></td>
      </tr>
      <tr>
        <td id="L283" class="blob-num js-line-number" data-line-number="283"></td>
        <td id="LC283" class="blob-code js-file-line"><span class="pl-s1">                                                  }}</span></td>
      </tr>
      <tr>
        <td id="L284" class="blob-num js-line-number" data-line-number="284"></td>
        <td id="LC284" class="blob-code js-file-line"><span class="pl-s1">                                         );</span></td>
      </tr>
      <tr>
        <td id="L285" class="blob-num js-line-number" data-line-number="285"></td>
        <td id="LC285" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L286" class="blob-num js-line-number" data-line-number="286"></td>
        <td id="LC286" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-k">if</span> (feature_count <span class="pl-k">&gt;</span> max_cluster) {</span></td>
      </tr>
      <tr>
        <td id="L287" class="blob-num js-line-number" data-line-number="287"></td>
        <td id="LC287" class="blob-code js-file-line"><span class="pl-s1">                jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#ds-hc<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&#39;</span>disabled<span class="pl-pds">&#39;</span></span>, <span class="pl-c1">true</span>);</span></td>
      </tr>
      <tr>
        <td id="L288" class="blob-num js-line-number" data-line-number="288"></td>
        <td id="LC288" class="blob-code js-file-line"><span class="pl-s1">            }</span></td>
      </tr>
      <tr>
        <td id="L289" class="blob-num js-line-number" data-line-number="289"></td>
        <td id="LC289" class="blob-code js-file-line"><span class="pl-s1">            <span class="pl-k">if</span> (feature_count <span class="pl-k">&gt;</span> <span class="pl-c1">3</span> <span class="pl-k">&amp;&amp;</span> feature_count <span class="pl-k">&lt;=</span> max_cluster) {</span></td>
      </tr>
      <tr>
        <td id="L290" class="blob-num js-line-number" data-line-number="290"></td>
        <td id="LC290" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.clusterSamples();</span></td>
      </tr>
      <tr>
        <td id="L291" class="blob-num js-line-number" data-line-number="291"></td>
        <td id="LC291" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.kmeansSamples();</span></td>
      </tr>
      <tr>
        <td id="L292" class="blob-num js-line-number" data-line-number="292"></td>
        <td id="LC292" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L293" class="blob-num js-line-number" data-line-number="293"></td>
        <td id="LC293" class="blob-code js-file-line"><span class="pl-s1">                <span class="pl-k">for</span> (<span class="pl-k">var</span> i<span class="pl-k">=</span><span class="pl-c1">4</span>; i <span class="pl-k">&lt;</span> feature_count; <span class="pl-k">++</span>i) {</span></td>
      </tr>
      <tr>
        <td id="L294" class="blob-num js-line-number" data-line-number="294"></td>
        <td id="LC294" class="blob-code js-file-line"><span class="pl-s1">                    jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#ds-km<span class="pl-pds">&#39;</span></span>).</span></td>
      </tr>
      <tr>
        <td id="L295" class="blob-num js-line-number" data-line-number="295"></td>
        <td id="LC295" class="blob-code js-file-line"><span class="pl-s1">                              append(jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>&lt;option&gt;&lt;/option&gt;<span class="pl-pds">&quot;</span></span>).</span></td>
      </tr>
      <tr>
        <td id="L296" class="blob-num js-line-number" data-line-number="296"></td>
        <td id="LC296" class="blob-code js-file-line"><span class="pl-s1">                              attr(<span class="pl-s"><span class="pl-pds">&quot;</span>value<span class="pl-pds">&quot;</span></span>,i).</span></td>
      </tr>
      <tr>
        <td id="L297" class="blob-num js-line-number" data-line-number="297"></td>
        <td id="LC297" class="blob-code js-file-line"><span class="pl-s1">                              text(i));</span></td>
      </tr>
      <tr>
        <td id="L298" class="blob-num js-line-number" data-line-number="298"></td>
        <td id="LC298" class="blob-code js-file-line"><span class="pl-s1">                }</span></td>
      </tr>
      <tr>
        <td id="L299" class="blob-num js-line-number" data-line-number="299"></td>
        <td id="LC299" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L300" class="blob-num js-line-number" data-line-number="300"></td>
        <td id="LC300" class="blob-code js-file-line"><span class="pl-s1">            } <span class="pl-k">else</span> {</span></td>
      </tr>
      <tr>
        <td id="L301" class="blob-num js-line-number" data-line-number="301"></td>
        <td id="LC301" class="blob-code js-file-line"><span class="pl-s1">                jQuery(<span class="pl-s"><span class="pl-pds">&quot;</span>#ds-km<span class="pl-pds">&quot;</span></span>).attr(<span class="pl-s"><span class="pl-pds">&#39;</span>disabled<span class="pl-pds">&#39;</span></span>, <span class="pl-c1">true</span>);</span></td>
      </tr>
      <tr>
        <td id="L302" class="blob-num js-line-number" data-line-number="302"></td>
        <td id="LC302" class="blob-code js-file-line"><span class="pl-s1">            }</span></td>
      </tr>
      <tr>
        <td id="L303" class="blob-num js-line-number" data-line-number="303"></td>
        <td id="LC303" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L304" class="blob-num js-line-number" data-line-number="304"></td>
        <td id="LC304" class="blob-code js-file-line"><span class="pl-s1">            hm_ds.draw();</span></td>
      </tr>
      <tr>
        <td id="L305" class="blob-num js-line-number" data-line-number="305"></td>
        <td id="LC305" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L306" class="blob-num js-line-number" data-line-number="306"></td>
        <td id="LC306" class="blob-code js-file-line"><span class="pl-s1">           jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#cl-hc<span class="pl-pds">&#39;</span></span>).change(<span class="pl-k">function</span>() {</span></td>
      </tr>
      <tr>
        <td id="L307" class="blob-num js-line-number" data-line-number="307"></td>
        <td id="LC307" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.linkage <span class="pl-k">=</span> <span class="pl-v">this</span>.<span class="pl-c1">value</span>;</span></td>
      </tr>
      <tr>
        <td id="L308" class="blob-num js-line-number" data-line-number="308"></td>
        <td id="LC308" class="blob-code js-file-line"><span class="pl-s1">                <span class="pl-k">if</span> (feature_count <span class="pl-k">&gt;=</span> <span class="pl-c1">3</span>) { hm_cl.clusterSamples(); }</span></td>
      </tr>
      <tr>
        <td id="L309" class="blob-num js-line-number" data-line-number="309"></td>
        <td id="LC309" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.clusterVariables();</span></td>
      </tr>
      <tr>
        <td id="L310" class="blob-num js-line-number" data-line-number="310"></td>
        <td id="LC310" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.draw();</span></td>
      </tr>
      <tr>
        <td id="L311" class="blob-num js-line-number" data-line-number="311"></td>
        <td id="LC311" class="blob-code js-file-line"><span class="pl-s1">           });</span></td>
      </tr>
      <tr>
        <td id="L312" class="blob-num js-line-number" data-line-number="312"></td>
        <td id="LC312" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L313" class="blob-num js-line-number" data-line-number="313"></td>
        <td id="LC313" class="blob-code js-file-line"><span class="pl-s1">           jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#cl-km<span class="pl-pds">&#39;</span></span>).change(<span class="pl-k">function</span>() {</span></td>
      </tr>
      <tr>
        <td id="L314" class="blob-num js-line-number" data-line-number="314"></td>
        <td id="LC314" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.kmeansClusters <span class="pl-k">=</span> <span class="pl-c1">parseInt</span>(<span class="pl-v">this</span>.<span class="pl-c1">value</span>);</span></td>
      </tr>
      <tr>
        <td id="L315" class="blob-num js-line-number" data-line-number="315"></td>
        <td id="LC315" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.kmeansSamples();</span></td>
      </tr>
      <tr>
        <td id="L316" class="blob-num js-line-number" data-line-number="316"></td>
        <td id="LC316" class="blob-code js-file-line"><span class="pl-s1">                <span class="pl-c">// hm_cl.kmeansVariables();</span></span></td>
      </tr>
      <tr>
        <td id="L317" class="blob-num js-line-number" data-line-number="317"></td>
        <td id="LC317" class="blob-code js-file-line"><span class="pl-s1">                hm_cl.draw();</span></td>
      </tr>
      <tr>
        <td id="L318" class="blob-num js-line-number" data-line-number="318"></td>
        <td id="LC318" class="blob-code js-file-line"><span class="pl-s1">           });</span></td>
      </tr>
      <tr>
        <td id="L319" class="blob-num js-line-number" data-line-number="319"></td>
        <td id="LC319" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L320" class="blob-num js-line-number" data-line-number="320"></td>
        <td id="LC320" class="blob-code js-file-line"><span class="pl-s1">           jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#ds-hc<span class="pl-pds">&#39;</span></span>).change(<span class="pl-k">function</span>() {</span></td>
      </tr>
      <tr>
        <td id="L321" class="blob-num js-line-number" data-line-number="321"></td>
        <td id="LC321" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.linkage <span class="pl-k">=</span> <span class="pl-v">this</span>.<span class="pl-c1">value</span>;</span></td>
      </tr>
      <tr>
        <td id="L322" class="blob-num js-line-number" data-line-number="322"></td>
        <td id="LC322" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.clusterSamples();</span></td>
      </tr>
      <tr>
        <td id="L323" class="blob-num js-line-number" data-line-number="323"></td>
        <td id="LC323" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.draw();</span></td>
      </tr>
      <tr>
        <td id="L324" class="blob-num js-line-number" data-line-number="324"></td>
        <td id="LC324" class="blob-code js-file-line"><span class="pl-s1">            });</span></td>
      </tr>
      <tr>
        <td id="L325" class="blob-num js-line-number" data-line-number="325"></td>
        <td id="LC325" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L326" class="blob-num js-line-number" data-line-number="326"></td>
        <td id="LC326" class="blob-code js-file-line"><span class="pl-s1">            jQuery(<span class="pl-s"><span class="pl-pds">&#39;</span>#ds-km<span class="pl-pds">&#39;</span></span>).change(<span class="pl-k">function</span>() {</span></td>
      </tr>
      <tr>
        <td id="L327" class="blob-num js-line-number" data-line-number="327"></td>
        <td id="LC327" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.kmeansClusters <span class="pl-k">=</span> <span class="pl-c1">parseInt</span>(<span class="pl-v">this</span>.<span class="pl-c1">value</span>);</span></td>
      </tr>
      <tr>
        <td id="L328" class="blob-num js-line-number" data-line-number="328"></td>
        <td id="LC328" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.kmeansSamples();</span></td>
      </tr>
      <tr>
        <td id="L329" class="blob-num js-line-number" data-line-number="329"></td>
        <td id="LC329" class="blob-code js-file-line"><span class="pl-s1">                hm_ds.draw();</span></td>
      </tr>
      <tr>
        <td id="L330" class="blob-num js-line-number" data-line-number="330"></td>
        <td id="LC330" class="blob-code js-file-line"><span class="pl-s1">           });</span></td>
      </tr>
      <tr>
        <td id="L331" class="blob-num js-line-number" data-line-number="331"></td>
        <td id="LC331" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L332" class="blob-num js-line-number" data-line-number="332"></td>
        <td id="LC332" class="blob-code js-file-line"><span class="pl-s1">     }</span></td>
      </tr>
      <tr>
        <td id="L333" class="blob-num js-line-number" data-line-number="333"></td>
        <td id="LC333" class="blob-code js-file-line"><span class="pl-s1"></span></td>
      </tr>
      <tr>
        <td id="L334" class="blob-num js-line-number" data-line-number="334"></td>
        <td id="LC334" class="blob-code js-file-line"><span class="pl-s1">    &lt;/<span class="pl-ent">script</span>&gt;</span></td>
      </tr>
      <tr>
        <td id="L335" class="blob-num js-line-number" data-line-number="335"></td>
        <td id="LC335" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L336" class="blob-num js-line-number" data-line-number="336"></td>
        <td id="LC336" class="blob-code js-file-line"><span class="pl-c">&lt;!-- /heatMap.jsp --&gt;</span></td>
      </tr>
</table>

  </div>

</div>

<a href="#jump-to-line" rel="facebox[.linejump]" data-hotkey="l" style="display:none">Jump to Line</a>
<div id="jump-to-line" style="display:none">
  <form accept-charset="UTF-8" action="" class="js-jump-to-line-form" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
    <input class="linejump-input js-jump-to-line-field" type="text" placeholder="Jump to line&hellip;" autofocus>
    <button type="submit" class="btn">Go</button>
</form></div>

        </div>

      </div><!-- /.repo-container -->
      <div class="modal-backdrop"></div>
    </div><!-- /.container -->
  </div><!-- /.site -->


    </div><!-- /.wrapper -->

      <div class="container">
  <div class="site-footer" role="contentinfo">
    <ul class="site-footer-links right">
        <li><a href="https://status.github.com/" data-ga-click="Footer, go to status, text:status">Status</a></li>
      <li><a href="https://developer.github.com" data-ga-click="Footer, go to api, text:api">API</a></li>
      <li><a href="https://training.github.com" data-ga-click="Footer, go to training, text:training">Training</a></li>
      <li><a href="https://shop.github.com" data-ga-click="Footer, go to shop, text:shop">Shop</a></li>
        <li><a href="https://github.com/blog" data-ga-click="Footer, go to blog, text:blog">Blog</a></li>
        <li><a href="https://github.com/about" data-ga-click="Footer, go to about, text:about">About</a></li>

    </ul>

    <a href="https://github.com" aria-label="Homepage">
      <span class="mega-octicon octicon-mark-github" title="GitHub"></span>
</a>
    <ul class="site-footer-links">
      <li>&copy; 2015 <span title="0.03617s from github-fe133-cp1-prd.iad.github.net">GitHub</span>, Inc.</li>
        <li><a href="https://github.com/site/terms" data-ga-click="Footer, go to terms, text:terms">Terms</a></li>
        <li><a href="https://github.com/site/privacy" data-ga-click="Footer, go to privacy, text:privacy">Privacy</a></li>
        <li><a href="https://github.com/security" data-ga-click="Footer, go to security, text:security">Security</a></li>
        <li><a href="https://github.com/contact" data-ga-click="Footer, go to contact, text:contact">Contact</a></li>
    </ul>
  </div>
</div>


    <div class="fullscreen-overlay js-fullscreen-overlay" id="fullscreen_overlay">
  <div class="fullscreen-container js-suggester-container">
    <div class="textarea-wrap">
      <textarea name="fullscreen-contents" id="fullscreen-contents" class="fullscreen-contents js-fullscreen-contents" placeholder=""></textarea>
      <div class="suggester-container">
        <div class="suggester fullscreen-suggester js-suggester js-navigation-container"></div>
      </div>
    </div>
  </div>
  <div class="fullscreen-sidebar">
    <a href="#" class="exit-fullscreen js-exit-fullscreen tooltipped tooltipped-w" aria-label="Exit Zen Mode">
      <span class="mega-octicon octicon-screen-normal"></span>
    </a>
    <a href="#" class="theme-switcher js-theme-switcher tooltipped tooltipped-w"
      aria-label="Switch themes">
      <span class="octicon octicon-color-mode"></span>
    </a>
  </div>
</div>



    
    

    <div id="ajax-error-message" class="flash flash-error">
      <span class="octicon octicon-alert"></span>
      <a href="#" class="octicon octicon-x flash-close js-ajax-error-dismiss" aria-label="Dismiss error"></a>
      Something went wrong with that request. Please try again.
    </div>


      <script crossorigin="anonymous" src="https://assets-cdn.github.com/assets/frameworks-2c8ae50712a47d2b83d740cb875d55cdbbb3fdbccf303951cc6b7e63731e0c38.js"></script>
      <script async="async" crossorigin="anonymous" src="https://assets-cdn.github.com/assets/github-8aff85e9cebe4485d56ccdb7ec1cddc29ae18de6dac4211b8d037d21b01d8642.js"></script>
      
      

  </body>
</html>

