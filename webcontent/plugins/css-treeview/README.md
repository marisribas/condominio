# Pure CSS Treeview

Lightweight Pure CSS treeview code based on the original code from The CSS Ninja.

## Install

You can install this package with `bower`.

### bower

```shell
bower install purecss-treeview
```

Add a `<link>` to your `index.html`:

```html
<link rel="stylesheet" type="text/css" href="bower-components/css-treeview/purecss-treeview.min.css" media="screen">
```

Then add the tree structure in your code by using a html list with `class="tree"` attribute:

```html
<ol class="tree">
  <li>
			<label for="folder2">Folder 2</label> <input type="checkbox" id="folder2" /> 
			<ol>
				<li><a href="">File 1</a></li>
				<li>
					<label for="subfolder2">Subfolder 1</label> <input type="checkbox" id="subfolder2" /> 
					<ol>
						<li class="file"><a href="">Subfile 1</a></li>
						<li class="file"><a href="">Subfile 2</a></li>
						<li class="file"><a href="">Subfile 3</a></li>
						<li class="file"><a href="">Subfile 4</a></li>
						<li class="file"><a href="">Subfile 5</a></li>
						<li class="file"><a href="">Subfile 6</a></li>
					</ol>
				</li>
			</ol>
		</li>
</ol>
```

## Roadmap

- Also make it suport `<ul>` lists
- CSS3 Animations
- Ready to use styles and themes
